package com.ishan.rd.beorg.service;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionType;
import com.arangodb.entity.KeyType;
import com.arangodb.entity.StreamTransactionEntity;
import com.arangodb.model.*;
import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.velocypack.VPack;
import com.arangodb.velocypack.VPackBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.entity.MedicalRecord;
import com.ishan.rd.beorg.entity.MedicalRecordReqDTO;
import com.ishan.rd.beorg.entity.edges.HavingIssueEdge;
import com.ishan.rd.beorg.repository.HavingIssueEdgeRepository;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
import com.ishan.rd.beorg.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    MedIssueTagRepository issueTagRepository;
    @Autowired
    ArangoOperations template;

    public MedicalRecord save1(MedicalRecord medicalRecord){



        issueTagRepository.saveAll(medicalRecord.getIssues());
        MedicalRecord createdMedicalRecord = recordRepository.save(medicalRecord);

        IssueTag issueTag = medicalRecord.getIssues().get(0);
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


    public void save2(MedicalRecordReqDTO medicalRecordReqDTO) throws JsonProcessingException {
        ArangoDatabase db = template.driver().db("beorgdb");
        template.collection(C_MED_ISSUES, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1));

        template.collection(C_MED_RECORD, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1));

        template.collection(E_HAVING_ISSUE, new CollectionCreateOptions().
                keyOptions(false, KeyType.autoincrement, 1,1).
                type(CollectionType.EDGES));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.valueToTree(medicalRecordReqDTO);
        String issuesJsonStr = objectMapper.writeValueAsString(
                ((ObjectNode)rootNode).remove("issues"));

        String medicalRecordJsonStr = objectMapper.writeValueAsString(rootNode);
        log.info(medicalRecordJsonStr);
        log.info(issuesJsonStr);

        /*medicalRecord.setTitle("title");
        VPack vPack = new VPack.Builder().build();
        vPack.serialize(medicalRecord);*/

       // template.collection("", new CollectionCreateOptions().keyOptions())
        final TransactionOptions options = new TransactionOptions()
                .writeCollections(C_MED_ISSUES, C_MED_RECORD, E_HAVING_ISSUE).
                        readCollections(C_MED_ISSUES, C_MED_RECORD);
        String action = "function () { "
                + "var db = require('internal').db;"
                + "var medDoc = db." + C_MED_RECORD + ".save("+medicalRecordJsonStr+");"
                + "let issDoc = db." + C_MED_ISSUES + ".save("+issuesJsonStr+");"
                + "return issDoc"
              //  + "`Insert {_from : ${medDoc._id} , _to : ${issDoc._id} } in "+ E_HAVING_ISSUE+"`"
               // + "db._query(`FOR y in issDoc "
                  //+ "`Insert {_from : ${medDoc._id} , _to : y._id } into "+ E_HAVING_ISSUE+"`"
              //  + "db." + E_HAVING_ISSUE + ".save({_from :"
             //   +  "${medDoc._id}, _to : y._id}));`"
                 + "}";

        String res  = db.transaction(action, String.class,options);
        log.info(res);
       // db.


    }
}
