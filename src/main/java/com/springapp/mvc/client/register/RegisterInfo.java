package com.springapp.mvc.client.register;

/**
 * Created by WU on 14/11/2015.
 */
public class RegisterInfo {
    String id;
    String objectInctance = "DummyVehicleSensor";
    String clientURI;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectInctance() {
        return objectInctance;
    }

    public void setObjectInctance(String objectInctance) {
        this.objectInctance = objectInctance;
    }

    public String getClientURI() {
        return clientURI;
    }

    public void setClientURI(String clientURI) {
        this.clientURI = clientURI;
    }

}
