package com.ishan.rd.beorg.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import lombok.Getter;
import lombok.Setter;

@Document @Getter @Setter
@HashIndex(fields = {"name"}, unique = true)
public class SpecialityTag {

    private String id;
    private String name;
}
