package com.ishan.rd.beorg.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalRecordReqDTO {

    private String userId = "1";
    private String title;
    private String personName;
    private List<String> category;
    private List<MedIssueReqDTO> issues;
    private List<String> medicines;
    private List<String> doctors;
    private String centerName;
    private String dateOfVisit;
    private String contact;
    private int fees;
    private String remarks;
    private List<String> imagePaths;

}
