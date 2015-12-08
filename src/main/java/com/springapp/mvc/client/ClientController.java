package com.springapp.mvc.client;


import com.springapp.mvc.client.DeviceManagement.ClientObject;
import com.springapp.mvc.client.bootstrap.Bootstrap;
import com.springapp.mvc.client.bootstrap.DAO.ClientDAO;
import com.springapp.mvc.client.register.RegisterInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/client")
public class ClientController {
    Map<String, Client> clientMap = new HashMap<String, Client>();
    final ClientDAO clientDAO = new ClientDAO();
    ExecutorService executor = Executors.newFixedThreadPool(4);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createClient(@RequestBody RegisterInfo registerInfo) {
        Client client = new Client(registerInfo);
        clientMap.put(registerInfo.getId(), client);
        clientDAO.saveClientObject(new ClientObject(registerInfo.getId()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ClientInfo getClientInfo(@PathVariable String id) {
        ClientInfo clientInfo = new ClientInfo();
        Client client = clientMap.get(id);
        clientInfo.setId(id);
        clientInfo.setBoostrapStatus(client.bootstrapStatus);
        clientInfo.setRegisterStatus(client.registerStatus);
        clientInfo.setServerMessage(clientDAO.getMessage(id));
        return clientInfo;
    }

    @RequestMapping(value = "/{id}/bootstrap", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void initialBootstrap(@PathVariable String id) {
        Client client = clientMap.get(id);
        client.factoryInitialBootstrap();
        clientDAO.saveBootstrap(client.getBootstrap());
    }

    @RequestMapping(value = "/{id}/bootstrap", method = RequestMethod.GET)
    public @ResponseBody
    Bootstrap getBootstrap(@PathVariable String id) {
        return clientDAO.getBootstrap(id);
    }

    @RequestMapping(value = "/{id}/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@PathVariable String id) {
        Client client = clientMap.get(id);
        String serverURL =  client.bootstrap.getServerURL() + "/register";
        HttpOperation.post(serverURL, client.getRegisterInfo());
        client.registerStatus = true;
    }

    @RequestMapping(value = "/{id}/deregister", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deregister(@PathVariable String id) {
        Client client = clientMap.get(id);
        String serverURL =  client.bootstrap.getServerURL() + "/deregister";
        HttpOperation.delete(serverURL, id);
        client.registerStatus = false;
    }

    @RequestMapping(value = "/{id}/read", method = RequestMethod.GET)
    public @ResponseBody ClientObject readResource(@PathVariable String id) {
        return clientDAO.getClientObject(id);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void readResource(@PathVariable String id, @RequestBody ClientObject clientObject) {
        clientDAO.saveClientObject(clientObject);
    }

    @RequestMapping(value = "/{id}/observe", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void startObserve(@PathVariable String id) {
        Client client = clientMap.get(id);
        client.setObserved(true);
        executor.execute(new ObserveThread(client, clientDAO));
    }

    @RequestMapping(value = "/{id}/stopobserve", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void stopObserve(@PathVariable String id) {
        Client client = clientMap.get(id);
        client.setObserved(false);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello Client!");
        return "hello";
    }


}