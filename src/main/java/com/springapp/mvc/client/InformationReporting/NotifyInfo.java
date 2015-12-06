package com.springapp.mvc.client.InformationReporting;

/**
 * Created by WU on 16/11/2015.
 */
public class NotifyInfo {
    String id;
    double timestamp;
    String temp;

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getOilTank() {
        return oilTank;
    }

    public void setOilTank(String oilTank) {
        this.oilTank = oilTank;
    }

    String gps;
    String oilTank;
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
