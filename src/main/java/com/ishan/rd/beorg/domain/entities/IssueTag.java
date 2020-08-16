package com.ishan.rd.beorg.domain.entities;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ishan.rd.beorg.domain.edges.BelongsToSpeciality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
@Document(value = "medIssueTag")
@HashIndex(fields = { "name"}, unique = true)
public class IssueTag {


    @JsonProperty @ArangoId
    private String _id;
    @JsonProperty
    private String name;

    @JsonProperty
    private String _key;

    @Relations(edges = BelongsToSpeciality.class, lazy = true)
    private SpecialityTag speciality;
    private boolean standard;
}