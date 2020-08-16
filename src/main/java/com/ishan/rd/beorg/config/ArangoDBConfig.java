package com.ishan.rd.beorg.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;

import java.util.ArrayList;
import java.util.Collection;

//@Configuration
public class ArangoDBConfig implements ArangoConfiguration {
    public static final String DB = "spring-test-db";

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder();
    }

    @Override
    public String database() {
        return DB;
    }

    @Override
    public Collection<Converter<?, ?>> customConverters() {
        final Collection<Converter<?, ?>> converters = new ArrayList<>();
       /* converters.add(new CustomMappingTest.CustomVPackReadTestConverter());
        converters.add(new CustomMappingTest.CustomVPackWriteTestConverter());
        converters.add(new CustomMappingTest.CustomDBEntityReadTestConverter());
        converters.add(new CustomMappingTest.CustomDBEntityWriteTestConverter());*/
        return converters;
    }


}
