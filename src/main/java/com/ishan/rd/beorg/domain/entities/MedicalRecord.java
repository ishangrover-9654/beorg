package com.ishan.rd.beorg.domain.entities;

import com.arangodb.entity.KeyType;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.ishan.rd.beorg.domain.UploadFileResponse;
import com.ishan.rd.beorg.domain.edges.HavingIssueEdge;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Document(allowUserKeys = true, keyIncrement = 1, keyType = KeyType.autoincrement)
@Getter @Setter @NoArgsConstructor
@HashIndex(fields = { "personName"})
public class MedicalRecord {
    @ArangoId
    private String _id;
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
    private List<UploadFileResponse> imageFiles;
}
