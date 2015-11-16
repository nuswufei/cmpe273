package com.springapp.mvc.server.bootstrap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 14/11/2015.
 */
public class ServerBootstrapDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    public ServerBootstrapDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClientURI connectionString = new MongoClientURI("mongodb://273lab:1234@ds053774.mongolab.com:53774/cmpe273");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("cmpe273");
        mongoCollection = database.getCollection("bootstrap");
    }
    public Bootstrap getBootstrap(String id) {
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document == null) return null;
        String jsonString = document.toJson();
        Bootstrap bootstrap = null;
        try {
            bootstrap = jacksonObjectMapper.readValue(jsonString, Bootstrap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bootstrap;
    }
}
