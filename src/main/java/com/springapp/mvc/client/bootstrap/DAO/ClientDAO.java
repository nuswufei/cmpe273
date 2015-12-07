package com.springapp.mvc.client.bootstrap.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.springapp.mvc.client.DeviceManagement.ClientObject;
import com.springapp.mvc.client.DeviceManagement.ServerMessage;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by WU on 6/12/2015.
 */
public class ClientDAO {
    static MongoCollection mongoCollection;
    static ObjectMapper jacksonObjectMapper;
    MongoDatabase db;
    public ClientDAO() {
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        db = mongoClient.getDatabase("273project");
    }
    public void saveBootstrap(Bootstrap bootstrap) {
        try {
            mongoCollection = db.getCollection("bootstrapCol");
            deleteBootstrap(bootstrap.getId());
            String jsonInString = jacksonObjectMapper.writeValueAsString(bootstrap);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteBootstrap(String id) {
        mongoCollection = db.getCollection("bootstrapCol");
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }

    public Bootstrap getBootstrap(String id) {
        mongoCollection = db.getCollection("bootstrapCol");
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

    public void saveMessage(ServerMessage serverMessage) {
        deleteMessage(serverMessage.getId());
        mongoCollection = db.getCollection("messageCol");
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(serverMessage);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void deleteMessage(String id) {
        mongoCollection = db.getCollection("messageCol");
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }
    public ServerMessage getMessage(String id) {
        mongoCollection = db.getCollection("messageCol");
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document == null) return null;
        ServerMessage serverMessage = null;
        try {
            serverMessage = jacksonObjectMapper.readValue(document.toJson(), ServerMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverMessage;
    }

    public void saveClientObject(ClientObject clientObject) {
        deleteClientObject(clientObject.getId());
        mongoCollection = db.getCollection("clientObjCol");
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(clientObject);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void deleteClientObject(String id) {
        mongoCollection = db.getCollection("clientObjCol");
        Document document = new Document();
        document.append("id", id);
        mongoCollection.deleteMany(document);
    }
    public ClientObject getClientObject(String id) {
        mongoCollection = db.getCollection("clientObjCol");
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
