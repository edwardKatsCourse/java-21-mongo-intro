package com.demo.mongo.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@Configuration
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Override
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI("");
        return new MongoClient(mongoClientURI);
    }

    @Override
    protected String getDatabaseName() {
        return "persons";
    }
}
