package com.ishan.rd.beorg.service;

import com.ishan.rd.beorg.batch.launcher.IssueDataImportLauncher;
import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
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

    public void saveAll(Collection<IssueTag> issueTagList){
        issueTagRepository.saveAll(issueTagList);
    }

    public Iterable<IssueTag> findAll() {
        return issueTagRepository.findAll();
    }

    public void importIssuesAndSpecialities() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {
        issueDataImportLauncher.runIssuesImportJob();
    }
}
