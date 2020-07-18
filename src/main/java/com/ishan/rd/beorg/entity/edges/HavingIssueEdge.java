package com.ishan.rd.beorg.entity.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.entity.MedicalRecord;
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
    private IssueTag issueTag;

    public HavingIssueEdge(MedicalRecord medicalRecord, IssueTag issueTag) {
        this.medicalRecord = medicalRecord;
        this.issueTag = issueTag;
    }
}
