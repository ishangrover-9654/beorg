package com.ishan.rd.beorg.service;

import com.ishan.rd.beorg.batch.launcher.IssueDataImportLauncher;
import com.ishan.rd.beorg.domain.entities.MedIssueTag;
import com.ishan.rd.beorg.repository.arango.MedIssueTagRepository;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MedIssueTagService {
    @Autowired
    MedIssueTagRepository issueTagRepository;
    @Autowired
    IssueDataImportLauncher issueDataImportLauncher;

    public void saveAll(Collection<MedIssueTag> issueTagList){
        issueTagRepository.saveAll(issueTagList);
    }

    public Iterable<MedIssueTag> findAll() {
        System.out.println("in findall");
        Iterable<MedIssueTag> medIssueTagList = issueTagRepository.findAll();

        return medIssueTagList;
    }

    public void importIssuesAndSpecialities() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {
        issueDataImportLauncher.runIssuesImportJob();
    }
}
