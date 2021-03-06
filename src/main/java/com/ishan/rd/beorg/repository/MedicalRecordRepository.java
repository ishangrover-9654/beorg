package com.ishan.rd.beorg.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ishan.rd.beorg.entity.MedicalRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends ArangoRepository<MedicalRecord, String> {
}
