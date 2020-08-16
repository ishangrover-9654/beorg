package com.ishan.rd.beorg.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter @ToString
public class MedicalRecordDto {

    private String _id;
    private String _key;
    private String userId = "1";
    private String title;
    private String personName;
    private List<String> category;
    private List<MedIssueDto> issues;
    private List<String> medicines;
    private List<String> doctors;
    private String centerName;
    private String dateOfVisit;
    private String contact;
    private int fees;
    private String remarks;
    private List<MultipartFile> imageFiles;

}
