package com.springapp.mvc.client.DeviceManagement;

/**
 * Created by WU on 6/12/2015.
 */
public class ServerMessage {

    String id;
    String gasstationMsg;
    String serviceMsg;
    public String getServiceMsg() {
        return serviceMsg;
    }

    public void setServiceMsg(String serviceMsg) {
        this.serviceMsg = serviceMsg;
    }

    public String getGasstationMsg() {
        return gasstationMsg;
    }

    public void setGasstationMsg(String gasstationMsg) {
        this.gasstationMsg = gasstationMsg;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
