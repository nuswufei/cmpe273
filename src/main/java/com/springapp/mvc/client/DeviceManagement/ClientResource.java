package com.springapp.mvc.client.DeviceManagement;

/**
 * Created by WU on 15/11/2015.
 */
public class ClientResource {
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    public String excute() {
        resource = null;
        return "excuted";
    }
    String resource;
}
