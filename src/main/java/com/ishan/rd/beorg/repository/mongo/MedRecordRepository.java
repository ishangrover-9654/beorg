package com.ishan.rd.beorg.repository.mongo;

import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedRecordRepository extends MongoRepository<MedicalRecord,String> {
}
