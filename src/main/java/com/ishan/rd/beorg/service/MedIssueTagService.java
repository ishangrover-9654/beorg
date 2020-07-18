package com.ishan.rd.beorg.service;

import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MedIssueTagService {
    @Autowired
    MedIssueTagRepository issueTagRepository;

    public void saveAll(Collection<IssueTag> issueTagList){
        issueTagRepository.saveAll(issueTagList);
    }

    public Iterable<IssueTag> findAll() {
        return issueTagRepository.findAll();
    }
}
