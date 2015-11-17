package com.springapp.mvc.server.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.springapp.mvc.server.NotifyInfo;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 16/11/2015.
 */
public class ServerNotifyinfoDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    public ServerNotifyinfoDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClientURI connectionString = new MongoClientURI("mongodb://273lab:1234@ds053774.mongolab.com:53774/cmpe273");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("cmpe273");
        mongoCollection = database.getCollection("notifyinfo");
    }
    public NotifyInfo getNotifyinfo(String id) {
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document == null) return null;
        String jsonString = document.toJson();
        NotifyInfo notifyInfo = null;
        try {
            notifyInfo = jacksonObjectMapper.readValue(jsonString, NotifyInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifyInfo;
    }
    public void save(NotifyInfo notifyInfo) {
        try {
            delete(notifyInfo.getId());
            String jsonInString = jacksonObjectMapper.writeValueAsString(notifyInfo);
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
}
