package com.springapp.mvc.client;

import com.springapp.mvc.client.DeviceManagement.ClientObject;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import com.springapp.mvc.client.register.RegisterInfo;


/**
 * Created by WU on 14/11/2015.
 */
public class Client {
    final static private String DEFAULT_REGISTERSERVER_URL = "http://localhost:8080/server/register";

    boolean registerStatus = false;
    boolean bootstrapStatus = false;

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    Bootstrap bootstrap = new Bootstrap();

    public String getId() {
        return id;
    }

    private final String id;

    private RegisterInfo registerInfo;

    private ClientObject clientObject;

    public Client(RegisterInfo registerInfo) {
        this.id = registerInfo.getId();
        this.registerInfo = registerInfo;
        clientObject = new ClientObject(this.id);
        clientObject.setId(id);
    }

    public void factoryInitialBootstrap() {
        bootstrap.setId(id);
        bootstrap.setServerURL(DEFAULT_REGISTERSERVER_URL);
        this.bootstrapStatus = true;
    }
}
