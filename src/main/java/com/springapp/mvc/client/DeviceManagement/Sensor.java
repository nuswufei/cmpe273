package com.springapp.mvc.client.DeviceManagement;

/**
 * Created by WU on 6/12/2015.
 */
public class Sensor {
    String resource;
    String attribute;
    Sensor(String resource, String attribute) {
        this.resource = resource;
        this.attribute = attribute;
    }
    Sensor() {

    }
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }



    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


}
