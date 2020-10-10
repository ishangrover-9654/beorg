package com.ishan.rd.beorg.repository.arango;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.domain.edges.HavingIssueEdge;
import org.springframework.stereotype.Repository;

@Repository
public interface HavingIssueEdgeRepository extends ArangoRepository<HavingIssueEdge, String> {
}
