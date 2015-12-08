package com.springapp.mvc.server;

/**
 * Created by WU on 16/11/2015.
 */
public class NotifyInfo {
    String id;
    double timestamp;
    ClientObject data;

    public ClientObject getData() {
        return data;
    }

    public void setData(ClientObject data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

}
