package com.springapp.mvc.client.bootstrap.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 14/11/2015.
 */
public class ClientBootstrapDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    public ClientBootstrapDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("bootstrapdb");
        mongoCollection = db.getCollection("bootstrapCol");
    }

    public void saveBootstrap(Bootstrap bootstrap) {
        try {
            deleteBootstrap(bootstrap.getId());
            String jsonInString = jacksonObjectMapper.writeValueAsString(bootstrap);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteBootstrap(String id) {
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }

    public Bootstrap getBootstrap(String id) {
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document == null) return null;
        Bootstrap bootstrap = null;
        try {
            bootstrap = jacksonObjectMapper.readValue(document.toJson(), Bootstrap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bootstrap;
    }
}
