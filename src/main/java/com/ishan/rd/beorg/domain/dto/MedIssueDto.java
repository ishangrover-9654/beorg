package com.ishan.rd.beorg.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MedIssueDto {
    private String _id;
    private String name;
    private String _key;
    private boolean standard;
}
