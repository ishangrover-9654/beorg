package com.ishan.rd.beorg.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.entity.IssueTag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedIssueTagRepository extends ArangoRepository<IssueTag, String> {

    Optional<IssueTag> findByName(String name);
}
