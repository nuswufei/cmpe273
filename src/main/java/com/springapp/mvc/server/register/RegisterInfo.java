package com.springapp.mvc.server.register;

/**
 * Created by WU on 14/11/2015.
 */
public class RegisterInfo {
    String id;
    String objectInctance = "DummyVehicleSensor";
    String clientURI;
    String vin;
    String manufactory;
    String model;
    String year;
    String owner;
    public RegisterInfo(String id, String vin, String manufactory, String model, String year, String owner) {
        this.id = id;
        this.vin = vin;
        this.manufactory = manufactory;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.clientURI = "http://localhost:8080/client/" + id;
    }
    public RegisterInfo() {

    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}