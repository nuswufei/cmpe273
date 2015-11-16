package com.springapp.mvc.client.DeviceManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WU on 15/11/2015.
 */
public class ClientInstanceAttribute {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ClientAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ClientAttribute> attributes) {
        this.attributes = attributes;
    }
    public String id;
    public List<ClientAttribute> attributes = new ArrayList<ClientAttribute>();
}
