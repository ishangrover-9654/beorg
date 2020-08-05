package com.ishan.rd.beorg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ishan.rd.beorg.entity.MedicalRecord;
import com.ishan.rd.beorg.entity.MedicalRecordReqDTO;
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
    public ResponseEntity<MedicalRecord> save2(@RequestBody MedicalRecordReqDTO medicalRecordReqDTO) throws JsonProcessingException {
         recordService.save2(medicalRecordReqDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
