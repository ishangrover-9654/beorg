package com.ishan.rd.beorg.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.domain.entities.MedIssueTag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedIssueTagRepository extends ArangoRepository<MedIssueTag, String> {

    Optional<MedIssueTag> findByName(String name);
}
