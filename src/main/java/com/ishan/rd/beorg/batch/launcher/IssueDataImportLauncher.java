package com.ishan.rd.beorg.batch.launcher;

import com.ishan.rd.beorg.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class IssueDataImportLauncher {


    private final Job job;

    private final JobLauncher jobLauncher;


    public IssueDataImportLauncher(@Qualifier("issuesImportJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    //@Scheduled(cron = "${csv.to.database.job.cron}")
    public void runIssuesImportJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {
        log.info(Constants.JOB_STARTED);
        jobLauncher.run(job, newExecution());
        log.info(Constants.JOB_FINISHED);
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("currentTime", new JobParameter(new Date()));
        parameters.put("maxSkippedRecords", new JobParameter(1000000L));
        return new JobParameters(parameters);
    }

}
