package com.springapp.mvc.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.mvc.client.Client;
import com.springapp.mvc.client.DeviceManagement.*;
import com.springapp.mvc.client.HttpOperation;
import com.springapp.mvc.client.register.RegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WU on 14/11/2015.
 */
public class UnitTest {
    public static void main(String[] args) {
        initClient();
        boostrapTest();
        //addDummyData();
        System.out.print("done");
    }
    private static void initClient() {
        RegisterInfo car1Register = new RegisterInfo("1",  "12345", "ford", "focus", "2009", "tom");
        RegisterInfo car2Register = new RegisterInfo("2", "54321", "honda", "civic", "2010", "jerry");
        HttpOperation.post("http://localhost:8080/client/create", car1Register);
        HttpOperation.post("http://localhost:8080/client/create", car2Register);
    }
    private static void addDummyData() {

    }
    private static void boostrapTest() {
    }

}
