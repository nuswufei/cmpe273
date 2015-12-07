package com.springapp.mvc.client;


import com.springapp.mvc.client.bootstrap.Bootstrap;
import com.springapp.mvc.client.bootstrap.DAO.ClientDAO;
import com.springapp.mvc.client.register.RegisterInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    Map<String, Client> clientMap = new HashMap<String, Client>();
    ClientDAO clientDAO = new ClientDAO();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Client createClient(@RequestBody RegisterInfo registerInfo) {
        Client client = new Client(registerInfo);
        clientMap.put(registerInfo.getId(), client);
        return client;
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello Client!");
        return "hello";
    }
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String postWelcome(ModelMap model) {
        model.addAttribute("message", "Hello Client!");
        return "hello";
    }
}