package com.ishan.rd.beorg.mapping;

import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import com.ishan.rd.beorg.domain.dto.MedicalRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalRecordMapper {

    MedicalRecord dtoToEntity(final MedicalRecordDto medicalRecordDTO);

}
