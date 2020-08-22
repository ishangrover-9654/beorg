package com.ishan.rd.beorg.domain.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.ishan.rd.beorg.domain.entities.MedIssueTag;
import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter @Setter
@Edge
public class HavingIssueEdge {
    @Id
    private String id;

    @From
    private MedicalRecord medicalRecord;

    @To
    private MedIssueTag issueTag;

    public HavingIssueEdge(MedicalRecord medicalRecord, MedIssueTag issueTag) {
        this.medicalRecord = medicalRecord;
        this.issueTag = issueTag;
    }
}
