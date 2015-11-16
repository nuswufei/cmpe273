package com.springapp.mvc.client.DeviceManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WU on 15/11/2015.
 */
public class ClientInstance {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ClientResource> getResources() {
        return resources;
    }

    public void setResources(List<ClientResource> resources) {
        this.resources = resources;
    }

    public String id;
    public List<ClientResource> resources = new ArrayList<ClientResource>();
}
