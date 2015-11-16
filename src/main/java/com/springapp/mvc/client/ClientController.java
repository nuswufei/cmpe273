package com.springapp.mvc.client;

import com.springapp.mvc.client.DeviceManagement.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    List<Client> clientList = new ArrayList<Client>();

    @RequestMapping(value = "/management/create/{id}", method = RequestMethod.GET)
    public void createClient(@PathVariable String id) {
        Client client = new Client(id);
        client.factoryInitialBootstrap();
        client.register();
        client.saveClientObject();
        clientList.add(client);

    }
    @RequestMapping(value = "/management/read/{objectid}", method = RequestMethod.GET)
    @ResponseBody
    List<ClientInstance> getAllInstance(@PathVariable String objectid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject =clientObjectDAO.get(objectid);
        return clientObject.getInstances();
    }
    @RequestMapping(value = "/management/read/{objectid}/{instanceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientInstance getInstance(@PathVariable String objectid, @PathVariable String instanceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        return clientInstance;
    }
    @RequestMapping(value = "/management/read/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientResource getResouce(@PathVariable String objectid, @PathVariable String instanceid,
                             @PathVariable String resourceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        ClientResource clientResource = clientInstance.getResources().get(Integer.parseInt(resourceid));
        return clientResource;
    }
    @RequestMapping(value = "/management/discover/{objectid}", method = RequestMethod.GET)
    @ResponseBody
    List<ClientAttribute> getAllAttributes(@PathVariable String objectid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject =clientObjectDAO.get(objectid);
        return clientObject.getAttributes();
    }
    @RequestMapping(value = "/management/discover/{objectid}/{instanceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientAttribute getInstanceAttribute(@PathVariable String objectid, @PathVariable String instanceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientAttribute clientAttribute = clientObject.getAttributes().get(Integer.parseInt(instanceid));
        return clientAttribute;
    }

    @RequestMapping(value = "/management/discover/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientResource getResourceAttribute(@PathVariable String objectid, @PathVariable String instanceid,
                              @PathVariable String resourceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientAttribute clientAttribute = clientObject.getAttributes().get(Integer.parseInt(instanceid));
        return clientAttribute.getResources().get(Integer.parseInt(resourceid));
    }

    @RequestMapping(value = "/management/write/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.POST)
    @ResponseBody
    ClientResource write(@PathVariable String objectid, @PathVariable String instanceid,
                                        @PathVariable String resourceid, @RequestParam("newvalue") String value) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        clientInstance.getResources().set(Integer.parseInt(resourceid), value);
    }

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello Client!");
		return "hello";
	}
}