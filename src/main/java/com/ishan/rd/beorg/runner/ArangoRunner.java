package com.ishan.rd.beorg.runner;

import com.ishan.rd.beorg.domain.entities.MedIssueTag;
import com.ishan.rd.beorg.repository.arango.MedIssueTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.ishan.rd.beorg")
public class ArangoRunner implements CommandLineRunner {

    @Autowired
    MedIssueTagRepository medIssueTagRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("here");
        MedIssueTag issueTag = new MedIssueTag();
        issueTag.setName("Fever");

        MedIssueTag issueTag1 = medIssueTagRepository.save(issueTag);
        System.out.println(issueTag1);
    }
}
