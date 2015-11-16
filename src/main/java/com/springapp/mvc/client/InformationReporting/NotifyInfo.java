package com.springapp.mvc.client.InformationReporting;

/**
 * Created by WU on 16/11/2015.
 */
public class NotifyInfo {
    String id;
    String timestamp;
    String temp;
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
