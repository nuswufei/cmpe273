package com.springapp.mvc.test;

import com.springapp.mvc.client.Client;
import com.springapp.mvc.client.DeviceManagement.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WU on 14/11/2015.
 */
public class UnitTest {
    public static void main(String[] args) {
        //boostrapTest();

        addDummyData();

    }
    private static void addDummyData() {
        ClientResource c0 = new ClientResource();
        c0.setResource("Bootstrap_Server_URL: http://localhost:8080/server/bootstrap/");
        ClientResource c1 = new ClientResource();
        c1.setResource("Registration_Server_URL: http://localhost:8080/server/register");
        ClientResource c2 = new ClientResource();
        c2.setResource("Resource_Server_URL: http://localhost:8080/server/");
        ClientResource c3 = new ClientResource();
        c3.setResource("VIN: 1234567");
        ClientResource c4 = new ClientResource();
        c4.setResource("manufacture: HONDA");
        ClientResource c5 = new ClientResource();
        c5.setResource("modelname: civic");
        ClientResource c6 = new ClientResource();
        c6.setResource("modeloption: ex");
        ClientResource c7 = new ClientResource();
        c7.setResource("temperature: 20");
        ClientInstance clientInstance = new ClientInstance();
        clientInstance.setId("0");
        List<ClientResource> lst = new ArrayList<ClientResource>();
        lst.add(c0);
        lst.add(c1);
        lst.add(c2);
        lst.add(c3);
        lst.add(c4);
        lst.add(c5);
        lst.add(c6);
        lst.add(c7);
        clientInstance.setResources(lst);

        ClientInstanceAttribute clientInstanceAttribute = new ClientInstanceAttribute();
        for(int i = 0; i < 7; ++i) {
            clientInstanceAttribute.getAttributes().add(new ClientAttribute());
        }
        ClientAttribute clientAttribute = new ClientAttribute();
        clientAttribute.setAttribute("pmin = 10, pmax = 60");
        clientInstanceAttribute.getAttributes().add(clientAttribute);
        clientInstanceAttribute.setId("0");
        ClientObject clientObject = new ClientObject();
        clientObject.getInstances().add(clientInstance);
        clientObject.getAttributes().add(clientInstanceAttribute);

        clientObject.setId("1");

        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        clientObjectDAO.save(clientObject);
        
    }
    private static void boostrapTest() {
        Client client = new Client("1");
        System.out.println(client.getBootstrapInfo());
        client.clientIntitialBootstrap();
        System.out.println(client.getBootstrapInfo());
        client.factoryInitialBootstrap();
        System.out.println(client.getBootstrapInfo());
    }

}
