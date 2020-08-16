package com.ishan.rd.beorg.mapping;

import com.arangodb.springframework.core.convert.DBDocumentEntity;
import com.ishan.rd.beorg.domain.entities.MedicalRecord;
import org.springframework.core.convert.converter.Converter;

public class CustomEntityMapper {

    public static class MedicalRecordConverter implements Converter<MedicalRecord, DBDocumentEntity> {

        @Override
        public DBDocumentEntity convert(MedicalRecord source) {
            final DBDocumentEntity entity = new DBDocumentEntity();
            entity.put("", "");
            return entity;
        }
        }
    }

