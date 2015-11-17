package com.springapp.mvc.client;

/**
 * Created by WU on 16/11/2015.
 */
public class NotifyCheater {
    public static void main(String[] args) {
        NotifyCheater notifyCheater = new NotifyCheater();
        try {
            notifyCheater.observe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void observe() throws InterruptedException {
        ObserveFlagDAO observeFlagDAO = new ObserveFlagDAO();
        ObserveFlag observeFlag = observeFlagDAO.get("1");
        while(observeFlag.getFlag() != null) {
            HttpOperation.get("http://localhost:8080/client/notify/1/0/7");
            Thread.sleep(5000);
        }

    }
}
