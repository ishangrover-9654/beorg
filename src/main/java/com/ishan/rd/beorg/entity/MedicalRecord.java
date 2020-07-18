package com.ishan.rd.beorg.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.ishan.rd.beorg.entity.edges.HavingIssueEdge;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document @Getter @Setter @NoArgsConstructor
@HashIndex(fields = { "personName", "dateOfVisit"}, unique = true)
public class MedicalRecord {
    @Id
    private String id;
    private String userId = "1";
    private String title;
    private String personName;
    private List<String> category;

    @Relations(edges = HavingIssueEdge.class, lazy = true)
    private List<IssueTag> issues;
    private List<String> medicines;
    private List<String> doctors;
    private String centerName;
    private String dateOfVisit;
    private String contact;
    private int fees;
    private String remarks;
    private List<String> imagePaths;
}
