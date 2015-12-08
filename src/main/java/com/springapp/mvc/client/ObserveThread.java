package com.springapp.mvc.client;

import com.springapp.mvc.client.InformationReporting.NotifyInfo;
import com.springapp.mvc.client.bootstrap.DAO.ClientDAO;

/**
 * Created by WU on 6/12/2015.
 */
public class ObserveThread implements Runnable {
    Client client;
    ClientDAO clientDAO;
    ObserveThread(Client client, ClientDAO clientDAO) {
        this.client = client;
        this.clientDAO = clientDAO;
    }
    @Override
    public void run() {
        while(client.isObserved()) {
            try {
                Thread.sleep(5000);

                NotifyInfo notifyInfo = new NotifyInfo();
                notifyInfo.setId(client.getId());
                notifyInfo.setTimestamp(System.currentTimeMillis());
                notifyInfo.setData(clientDAO.getClientObject(client.getId()));

                String url = client.getBootstrap().getServerURL() + "/notify";

                HttpOperation.post(url, notifyInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
