package com.ishan.rd.beorg.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.entity.edges.HavingIssueEdge;
import org.springframework.stereotype.Repository;

@Repository
public interface HavingIssueEdgeRepository extends ArangoRepository<HavingIssueEdge, String> {
}
