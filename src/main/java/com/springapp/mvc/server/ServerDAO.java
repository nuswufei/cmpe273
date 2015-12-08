package com.springapp.mvc.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.springapp.mvc.server.register.RegisterInfo;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 6/12/2015.
 */
public class ServerDAO {
    static MongoCollection mongoCollection;
    MongoDatabase database;
    static ObjectMapper jacksonObjectMapper;
    public ServerDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClientURI connectionString = new MongoClientURI("mongodb://273lab:1234@ds053774.mongolab.com:53774/cmpe273");
        MongoClient mongoClient = new MongoClient(connectionString);
        database = mongoClient.getDatabase("cmpe273");
    }
    public void saveRegisterInfo(RegisterInfo registerInfo) {
        try {
            deleteRegisterInfo(registerInfo.getId());
            mongoCollection = database.getCollection("registerinfoCol");
            String jsonInString = jacksonObjectMapper.writeValueAsString(registerInfo);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void deleteRegisterInfo(String id) {
        mongoCollection = database.getCollection("registerinfoCol");
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }
    public RegisterInfo getRegisterInfo(String id) {
        mongoCollection = database.getCollection("registerinfoCol");
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        try {
            return jacksonObjectMapper.readValue(document.toJson(), RegisterInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void saveNotifyInfo(NotifyInfo notifyInfo) {
        try {
            mongoCollection = database.getCollection("notifyInfoCol");
            String jsonInString = jacksonObjectMapper.writeValueAsString(notifyInfo);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



}
