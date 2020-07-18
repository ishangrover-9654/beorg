package com.ishan.rd.beorg.config;

import com.ishan.rd.beorg.batch.reader.IssueCsvFileReader;
import com.ishan.rd.beorg.batch.writer.IssueImportArangoWriter;
import com.ishan.rd.beorg.entity.IssueImportDTO;
import com.ishan.rd.beorg.entity.IssueTag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@EnableBatchProcessing
//@Import({InfrastructureConfiguration.class})
public class IssuesBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    FlatFileItemReader<IssueImportDTO> bookDateItemReader(Environment environment) {
        return new IssueCsvFileReader("./data/profiles.csv");
    }

    @Bean
    Job issuesImportJob(JobBuilderFactory jobBuilderFactory,
                         @Qualifier("updateIssueDataStep") Step updateIssueDataStep) {
        return jobBuilderFactory.get("issuesImportJob")
                .incrementer(new RunIdIncrementer())
                // .listener(bookingFileJobListener)
                .start(updateIssueDataStep)
                .build();
    }

    @Bean
    Step updateIssueDataStep(ItemReader<IssueImportDTO> profileItemReader,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("updateIssueDataStep")
                .<IssueImportDTO, IssueTag>chunk(1)
                .reader(profileItemReader)
                .writer(writer())
                //.processor(profileDataProcessor)
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<IssueTag> writer() {
        return new IssueImportArangoWriter();
    }
}