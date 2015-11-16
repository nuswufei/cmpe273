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
        List<String> l0 = new ArrayList<String>();
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        List<String> l3 = new ArrayList<String>();
        List<String> l4 = new ArrayList<String>();
        List<String> l5 = new ArrayList<String>();
        List<String> l6 = new ArrayList<String>();
        List<String> l7 = new ArrayList<String>();
        l0.add("Bootstrap_Server_URL: http://localhost:8080/server/bootstrap/");
        l1.add("Registration_Server_URL: http://localhost:8080/server/register");
        l2.add("Resource_Server_URL: http://localhost:8080/server/");
        l3.add("VIN: 1234567");
        l4.add("manufacture: HONDA");
        l5.add("modelname: civic");
        l6.add("modeloption: ex");
        l7.add("temperature: 20");
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

        ClientAttribute clientAttribute = new ClientAttribute();
        for(int i = 0; i < 7; ++i) {
            clientAttribute.getResources().add(new ClientResource());
        }
        ClientResource clientResource = new ClientResource();
        clientResource.getResources().add("pmin = 10");
        clientResource.getResources().add("pmax = 60");
        clientAttribute.getResources().add(clientResource);
        clientAttribute.setId("1");
        ClientObject clientObject = new ClientObject();
        clientObject.getInstances().add(clientInstance);
        clientObject.getAttributes().add(clientAttribute);

        clientObject.setId("0");

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
