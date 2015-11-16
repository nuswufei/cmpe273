package com.springapp.mvc.client.DeviceManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WU on 15/11/2015.
 */
public class ClientObject {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ClientInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ClientInstance> instances) {
        this.instances = instances;
    }

    public List<ClientAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ClientAttribute> attributes) {
        this.attributes = attributes;
    }

    public String id;
    public List<ClientInstance> instances = new ArrayList<ClientInstance>();
    public List<ClientAttribute> attributes = new ArrayList<ClientAttribute>();
}
