package com.ishan.rd.beorg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import com.ishan.rd.beorg.domain.dto.MedicalRecordDto;
import com.ishan.rd.beorg.service.MedIssueTagService;
import com.ishan.rd.beorg.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/medical/records")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    MedIssueTagService issueTagService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MedicalRecord> save2(@RequestPart("medicalRecord") MedicalRecordDto medicalRecordDTO,
                                               @RequestPart(value = "files") MultipartFile[] imageFiles) {
        //System.out.println(imageFiles[0].getName());
        System.out.println(imageFiles.length);
        medicalRecordService.save2(medicalRecordDTO, imageFiles );
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
