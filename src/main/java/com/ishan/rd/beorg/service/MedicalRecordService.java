package com.ishan.rd.beorg.service;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.*;
import com.arangodb.model.*;
import com.arangodb.springframework.core.ArangoOperations;
import com.ishan.rd.beorg.domain.UploadFileResponse;
import com.ishan.rd.beorg.domain.dto.BaseEdgeDto;
import com.ishan.rd.beorg.domain.entities.MedIssueTag;
import com.ishan.rd.beorg.domain.dto.MedIssueDto;
import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import com.ishan.rd.beorg.domain.dto.MedicalRecordDto;
import com.ishan.rd.beorg.domain.edges.HavingIssueEdge;
import com.ishan.rd.beorg.mapping.MedicalRecordMapper;
import com.ishan.rd.beorg.repository.HavingIssueEdgeRepository;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
import com.ishan.rd.beorg.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @Slf4j
public class MedicalRecordService {
    private static final String C_MED_RECORD = "medicalRecord";
    private static final String C_MED_ISSUES = "medIssueTag";
    private static final String E_HAVING_ISSUE = "havingIssueEdge";

    @Autowired
    MedicalRecordRepository recordRepository;
    @Autowired
    HavingIssueEdgeRepository havingIssueEdgeRepository;
    @Autowired
    MedIssueTagRepository medIssueTagRepository;
    @Autowired
    ArangoOperations template;
    @Autowired
    MedicalRecordMapper medicalRecordMapper;
    @Autowired
    private FileStorageService fileStorageService;

    public MedicalRecord save1(MedicalRecord medicalRecord){



        medIssueTagRepository.saveAll(medicalRecord.getIssues());
        MedicalRecord createdMedicalRecord = recordRepository.save(medicalRecord);

        MedIssueTag issueTag = medicalRecord.getIssues().get(0);
        /*Optional<IssueTag> medIssueTagOptional = issueTagRepository.findByName(issueTag.getName());
        medIssueTagOptional.ifPresent( medIssueTag1 ->
                havingIssueEdgeRepository.save(new HavingIssueEdge(createdMedicalRecord, medIssueTag1)));*/
        /**
         * Map each medical issue tag to created medical record by creating an edge
         * between from medicalRecord to each medical issue
         */
        List<HavingIssueEdge> havingIssueEdges = medicalRecord.getIssues().stream().
                map(medIssueTag -> new HavingIssueEdge(createdMedicalRecord, medIssueTag))
                .collect(Collectors.toList());




        Iterable<HavingIssueEdge> havingIssueEdges1 = havingIssueEdgeRepository.saveAll(havingIssueEdges);

        return createdMedicalRecord;
    }

    public void save(MedicalRecord medicalRecord){
        ArangoDatabase db = template.driver().db();

        StreamTransactionEntity tx1 =db.beginStreamTransaction(new StreamTransactionOptions().readCollections("").
                writeCollections(""));

        db.collection("collection")
                .insertDocument(new BaseDocument(), new DocumentCreateOptions().streamTransactionId(tx1.getId()));
        db.commitStreamTransaction(tx1.getId());
        StreamTransactionEntity tx = db.getStreamTransaction(tx1.getId());
        //assertThat(tx.getStatus(), is(StreamTransactionEntity.StreamTransactionStatus.committed));

        StreamTransactionEntity tx2 = db.beginStreamTransaction(
                new StreamTransactionOptions().readCollections("collection").writeCollections("collection"));
        final Map<String, Object> bindVars = new HashMap<>();
        bindVars.put("@collection", "");
        bindVars.put("key", "myKey");
        ArangoCursor<BaseDocument> cursor = db
                .query("FOR doc IN @@collection FILTER doc._key == @key RETURN doc", bindVars,
                        new AqlQueryOptions().streamTransactionId(tx2.getId()), BaseDocument.class);
        db.abortStreamTransaction(tx2.getId());
    }


    public void save2(MedicalRecordDto medicalRecordDto, MultipartFile[] imageFiles) {

        //TODO - Create below schema at start up
        ArangoDatabase db = template.driver().db("beorgdb");
        template.collection(C_MED_ISSUES, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1));

        template.collection(C_MED_RECORD, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1));

        template.collection(E_HAVING_ISSUE, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1).
                type(CollectionType.EDGES));

        //TODO - Explore through db.transactions() or better way to perform transactions
        StreamTransactionEntity tx =db.beginStreamTransaction(new StreamTransactionOptions()
        .readCollections().writeCollections(C_MED_ISSUES, C_MED_RECORD, E_HAVING_ISSUE).
                readCollections(C_MED_ISSUES, C_MED_RECORD));

        DocumentCreateOptions opts = new DocumentCreateOptions().streamTransactionId(tx.getId());

        List<UploadFileResponse> uploadFileResponse = Arrays.asList(imageFiles).stream().
                map(file -> uploadFile(file)).collect(Collectors.toList());
        //Create medical record
        MedicalRecord medicalRecord = medicalRecordMapper.dtoToEntity(medicalRecordDto);
        medicalRecord.setImageFiles(uploadFileResponse);
        DocumentCreateEntity<MedicalRecord> txMedRec =
                db.collection(C_MED_RECORD).insertDocument(medicalRecord, opts);

        //Create new Issue tags if any
        MultiDocumentEntity<DocumentCreateEntity<MedIssueDto>> txIssueTag  =
                db.collection(C_MED_ISSUES).insertDocuments(medicalRecordDto.getIssues(),
                opts.overwriteMode(OverwriteMode.ignore));

        //Create Edge for Medical record with multiple issue tags
        List<BaseEdgeDto> havingIssueEdges = txIssueTag.getDocuments().stream().
                map(doc -> new BaseEdgeDto(txMedRec.getId(), doc.getId())).collect(Collectors.toList());
        db.collection(E_HAVING_ISSUE).insertDocuments(havingIssueEdges, opts);


        db.commitStreamTransaction(tx.getId());

    }

    public UploadFileResponse uploadFile(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
