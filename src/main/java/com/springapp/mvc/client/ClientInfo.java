package com.springapp.mvc.client;

import com.springapp.mvc.client.DeviceManagement.ServerMessage;

/**
 * Created by WU on 6/12/2015.
 */
public class ClientInfo {
    ClientInfo() {

    }

    String id;
    ServerMessage serverMessage;
    boolean boostrapStatus;
    boolean registerStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ServerMessage getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(ServerMessage serverMessage) {
        this.serverMessage = serverMessage;
    }

    public boolean isBoostrapStatus() {
        return boostrapStatus;
    }

    public void setBoostrapStatus(boolean boostrapStatus) {
        this.boostrapStatus = boostrapStatus;
    }

    public boolean isRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }


}
