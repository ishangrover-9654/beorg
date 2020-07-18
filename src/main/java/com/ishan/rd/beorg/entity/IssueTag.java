package com.ishan.rd.beorg.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ishan.rd.beorg.entity.edges.BelongsToSpeciality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter @Setter @NoArgsConstructor
@Document
@HashIndex(fields = { "name"}, unique = true)
public class IssueTag {

    @Id
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;

    @Relations(edges = BelongsToSpeciality.class, lazy = true)
    private List<SpecialityTag> speciality;
    private boolean standard;
}