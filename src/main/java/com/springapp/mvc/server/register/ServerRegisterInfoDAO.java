package com.springapp.mvc.server.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 15/11/2015.
 */
public class ServerRegisterInfoDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    public ServerRegisterInfoDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClientURI connectionString = new MongoClientURI("mongodb://273lab:1234@ds053774.mongolab.com:53774/cmpe273");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("cmpe273");
        mongoCollection = database.getCollection("register");
    }
    public void saveRegisterInfo(RegisterInfo registerInfo) {
        try {
            deleteRegisterInfo(registerInfo.getId());
            String jsonInString = jacksonObjectMapper.writeValueAsString(registerInfo);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void deleteRegisterInfo(String id) {
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }
    public RegisterInfo getRegisterInfo(String id) {
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        try {
            return jacksonObjectMapper.readValue(document.toJson(), RegisterInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
