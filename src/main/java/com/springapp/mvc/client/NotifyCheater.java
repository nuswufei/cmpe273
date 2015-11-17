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
        while(true) {
            HttpOperation.get("http://localhost:8080/client/notify/1/0/7");
            System.out.println("send request");
            Thread.sleep(5000);
        }

    }
}
