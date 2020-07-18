package com.ishan.rd.beorg.controller;

import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.service.MedIssueTagService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/medical/issues")
public class MedIssueTagController {

    @Autowired
    MedIssueTagService issueTagService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody List<IssueTag> issueTagList){
        issueTagService.saveAll(issueTagList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IssueTag>> getAll(){
        //System.out.println("in get all");
        Iterable<IssueTag> issueTags = issueTagService.findAll();
        //System.out.println(issueTags);
        return new ResponseEntity(issueTags, HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importIssues() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {
        issueTagService.importIssuesAndSpecialities();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
