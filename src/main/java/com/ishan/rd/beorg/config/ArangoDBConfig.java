package com.ishan.rd.beorg.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.config.ArangoConfiguration;

public class ArangoDBConfig implements ArangoConfiguration {
    @Override
    public ArangoDB.Builder arango() {
        return null;
    }

    @Override
    public String database() {
        return null;
    }
}
