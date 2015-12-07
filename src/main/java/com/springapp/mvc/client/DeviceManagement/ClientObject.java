package com.springapp.mvc.client.DeviceManagement;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Map<String, Sensor> sensors) {
        this.sensors = sensors;
    }

    String id;
    Map<String , Sensor> sensors = new HashMap<String, Sensor>();
    public ClientObject() {

    }
    public ClientObject(String id) {
        this.id = id;
        Sensor mileage = new Sensor("100", "gt=0");
        Sensor oil = new Sensor("2.5", "gt=0; lt=10");
        Sensor gps = new Sensor("37.3353, -121.8813", "pmin=10");
        Sensor tirepressure = new Sensor("31", "gt=30; lt=32");
        Sensor enginetemperature = new Sensor("100", "gt=90; lt=120");

        this.sensors.put("mileage", mileage);
        this.sensors.put("oil", oil);
        this.sensors.put("gps", gps);
        this.sensors.put("tirepressure", tirepressure);
        this.sensors.put("enginetemperature", enginetemperature);
    }
}
