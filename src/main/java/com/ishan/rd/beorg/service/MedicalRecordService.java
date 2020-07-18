package com.ishan.rd.beorg.service;

import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.entity.MedicalRecord;
import com.ishan.rd.beorg.entity.edges.HavingIssueEdge;
import com.ishan.rd.beorg.repository.HavingIssueEdgeRepository;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
import com.ishan.rd.beorg.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository recordRepository;
    @Autowired
    HavingIssueEdgeRepository havingIssueEdgeRepository;
    @Autowired
    MedIssueTagRepository issueTagRepository;

    public MedicalRecord save(MedicalRecord medicalRecord){

        issueTagRepository.saveAll(medicalRecord.getIssues());
        MedicalRecord createdMedicalRecord = recordRepository.save(medicalRecord);

        IssueTag issueTag = medicalRecord.getIssues().get(0);
        Optional<IssueTag> medIssueTagOptional = issueTagRepository.findByName(issueTag.getName());
        medIssueTagOptional.ifPresent( medIssueTag1 ->
                havingIssueEdgeRepository.save(new HavingIssueEdge(createdMedicalRecord, medIssueTag1)));
        /**
         * Map each medical issue tag to created medical record by creating an edge
         * between from medicalRecord to each medical issue
         */
        /*List<HavingIssueEdge> havingIssueEdges = medicalRecord.getIssues().stream().
                map(medIssueTag -> new HavingIssueEdge(createdMedicalRecord, medIssueTag))
                .collect(Collectors.toList());*/

        //Iterable<HavingIssueEdge> havingIssueEdges1 = havingIssueEdgeRepository.saveAll(havingIssueEdges);

        return createdMedicalRecord;
    }
}
