package com.springapp.mvc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.mvc.client.DeviceManagement.ClientObject;
import com.springapp.mvc.client.DeviceManagement.ClientObjectDAO;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import com.springapp.mvc.client.bootstrap.ClientBootstrapInterface;
import com.springapp.mvc.client.bootstrap.DAO.ClientBootstrapDAO;
import com.springapp.mvc.client.register.ClientRegisterInterface;
import com.springapp.mvc.client.register.RegisterInfo;

import java.io.IOException;

/**
 * Created by WU on 14/11/2015.
 */
public class Client implements ClientBootstrapInterface, ClientRegisterInterface {
    final static private String BOOTSTRAPSERVER_URL = "http://localhost:8080/server/bootstrap/";
    final static private String DEFAULT_REGISTERSERVER_URL = "http://localhost:8080/server/register";
    final static private String CLIENT_URL = "http://localhost:8080/client/";

    public String temp = null;
    private Bootstrap bootstrap = new Bootstrap();
    private final String id;
    private ClientBootstrapDAO clientBootstrapDAO;
    private ObjectMapper jacksonObjectMapper;

    private RegisterInfo registerInfo;

    private ClientObject clientObject;

    public Client(String id) {
        this.id = id;
        clientBootstrapDAO = new ClientBootstrapDAO();
        jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        registerInfo = new RegisterInfo();
        registerInfo.setId(id);
        registerInfo.setClientURI(CLIENT_URL);
        clientObject = new ClientObject();
        clientObject.setId(id);
    }

    @Override
    public String getBootstrapInfo() {
        Bootstrap bootstrap = clientBootstrapDAO.getBootstrap(id);
        try {
            return jacksonObjectMapper.writeValueAsString(bootstrap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void factoryInitialBootstrap() {
        bootstrap.setId(id);
        bootstrap.setServerURL(DEFAULT_REGISTERSERVER_URL);
        bootstrap.setManufacture("honda");
        bootstrap.setModel("civic");
        clientBootstrapDAO.saveBootstrap(bootstrap);
    }

    @Override
    public void clientIntitialBootstrap() {
        String boostrapJson = HttpOperation.get(BOOTSTRAPSERVER_URL + id);
        bootstrap = null;
        try {
            bootstrap = jacksonObjectMapper.readValue(boostrapJson, Bootstrap.class);
            clientBootstrapDAO.saveBootstrap(bootstrap);
            temp = boostrapJson;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void register() {
        String registerInfoJson = null;
        try {
            registerInfoJson = jacksonObjectMapper.writeValueAsString(registerInfo);
            HttpOperation.post(bootstrap.getServerURL(), registerInfoJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        registerInfo.setObjectInctance("DummyVehicleSensor2.0");
        register();
    }

    @Override
    public void deregister() {
        HttpOperation.delete(bootstrap.getServerURL(), id);
    }

    public void saveClientObject() {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        clientObjectDAO.save(clientObject);
    }

}
