package com.ishan.rd.beorg.batch.writer;

import com.ishan.rd.beorg.entity.IssueTag;
import com.ishan.rd.beorg.repository.MedIssueTagRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IssueImportArangoWriter implements ItemWriter<IssueTag> {

    @Autowired
    MedIssueTagRepository issueTagRepository;

    @Override
    public void write(List<? extends IssueTag> list) throws Exception {
        issueTagRepository.saveAll(list);
    }
}
