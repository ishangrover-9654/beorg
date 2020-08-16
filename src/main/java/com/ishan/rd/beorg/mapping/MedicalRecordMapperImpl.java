package com.ishan.rd.beorg.mapping;

import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import com.ishan.rd.beorg.domain.dto.MedicalRecordDto;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapperImpl implements MedicalRecordMapper{

    @Override
    public MedicalRecord dtoToEntity(MedicalRecordDto source) {
        MedicalRecord dest = new MedicalRecord();
        dest.setTitle(source.getTitle());
        dest.setCategory(source.getCategory());
        dest.setCenterName(source.getCenterName());
        dest.setContact(source.getContact());
        dest.setDateOfVisit(source.getDateOfVisit());
        dest.setDoctors(source.getDoctors());
        dest.setFees(source.getFees());
        //dest.setImagePaths(source.getImagePaths());
        dest.setRemarks(dest.getRemarks());
        dest.setPersonName(source.getPersonName());
        return dest;
    }
}
