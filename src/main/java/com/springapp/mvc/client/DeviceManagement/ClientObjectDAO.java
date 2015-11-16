package com.springapp.mvc.client.DeviceManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 15/11/2015.
 */
public class ClientObjectDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    public ClientObjectDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("managementdb");
        mongoCollection = db.getCollection("testColl");
    }
    public void save(ClientObject clientObject) {
        delete(clientObject.getId());
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(clientObject);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void delete(String id) {
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }
    public ClientObject get(String id) {
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document == null) return null;
        ClientObject clientObject = null;
        try {
            clientObject = jacksonObjectMapper.readValue(document.toJson(), ClientObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientObject;
    }
}
