package com.ishan.rd.beorg.controller;

import com.ishan.rd.beorg.entity.MedicalRecord;
import com.ishan.rd.beorg.entity.edges.HavingIssueEdge;
import com.ishan.rd.beorg.service.MedIssueTagService;
import com.ishan.rd.beorg.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/medical/records")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService recordService;
    @Autowired
    MedIssueTagService issueTagService;

    @PostMapping
    public ResponseEntity<MedicalRecord> save(@RequestBody MedicalRecord medicalRecord){
        MedicalRecord medicalRecord1 = recordService.save( medicalRecord);
        return new ResponseEntity(medicalRecord1,HttpStatus.CREATED);
    }
}
