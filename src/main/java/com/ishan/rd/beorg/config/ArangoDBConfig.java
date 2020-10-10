package com.ishan.rd.beorg.config;

import com.arangodb.ArangoDB;
import com.arangodb.Protocol;
import com.arangodb.springframework.annotation.EnableArangoAuditing;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import com.ishan.rd.beorg.domain.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
//@EnableArangoAuditing(auditorAwareRef = "auditorProvider")
//@EnableArangoRepositories(basePackages = {
  //      "com.ishan.rd.beorg.repository.arango"})
public class ArangoDBConfig implements ArangoConfiguration {
    public static final String DB = "beorgdb";

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host("127.0.0.1", 8529)
                .useProtocol(Protocol.HTTP_JSON)
                .user("root")
                .password("123456");
    }

    @Override
    public String database() {
        return DB;
    }

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorProvider();
    }
}
