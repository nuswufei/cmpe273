package com.springapp.mvc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.mvc.client.DeviceManagement.*;
import com.springapp.mvc.server.NotifyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    Map<String, Client> clientList = new HashMap<String, Client>();
    private boolean notifyBoolean = false;
    ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
    @RequestMapping(value = "/management/create/{id}", method = RequestMethod.POST)
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
    List<ClientInstanceAttribute> getAllAttributes(@PathVariable String objectid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject =clientObjectDAO.get(objectid);
        return clientObject.getAttributes();
    }
    @RequestMapping(value = "/management/discover/{objectid}/{instanceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientInstanceAttribute getInstanceAttribute(@PathVariable String objectid, @PathVariable String instanceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstanceAttribute clientInstanceAttribute = clientObject.getAttributes().get(Integer.parseInt(instanceid));
        return clientInstanceAttribute;
    }

    @RequestMapping(value = "/management/discover/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    @ResponseBody
    ClientAttribute getResourceAttribute(@PathVariable String objectid, @PathVariable String instanceid,
                              @PathVariable String resourceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstanceAttribute clientInstanceAttribute = clientObject.getAttributes().get(Integer.parseInt(instanceid));
        return clientInstanceAttribute.getAttributes().get(Integer.parseInt(resourceid));
    }

    @RequestMapping(value = "/management/write/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.POST)
    @ResponseBody
    ClientObject write(@PathVariable String objectid, @PathVariable String instanceid,
                                        @PathVariable String resourceid, @RequestParam("newvalue") String value) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        clientInstance.getResources().get(Integer.parseInt(resourceid)).setResource(value);
        clientObjectDAO.save(clientObject);
        return clientObjectDAO.get(objectid);
    }

    @RequestMapping(value = "/management/writeattributes/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.POST)
    @ResponseBody
    ClientObject writeattributes(@PathVariable String objectid, @PathVariable String instanceid,
                       @PathVariable String resourceid, @RequestParam("newvalue") String value) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        List<ClientAttribute> attributes
                = clientObject.getAttributes().get(Integer.parseInt(instanceid)).getAttributes();
        attributes.get(Integer.parseInt(resourceid)).setAttribute(value);
        clientObjectDAO.save(clientObject);
        return clientObjectDAO.get(objectid);
    }

    @RequestMapping(value = "/management/excute/{objectid}/{instanceid}/{resourceid}", method = RequestMethod.POST)
    @ResponseBody
    ClientObject excuteResource(@PathVariable String objectid, @PathVariable String instanceid,
                                 @PathVariable String resourceid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        ClientObject clientObject = clientObjectDAO.get(objectid);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        ClientResource clientResource = clientInstance.getResources().get(Integer.parseInt((resourceid)));
        clientResource.excute();
        clientObjectDAO.save(clientObject);
        return clientObjectDAO.get(objectid);
    }
    @RequestMapping(value = "/management/delete/{objectid}", method = RequestMethod.DELETE)
    void deleteObject(@PathVariable String objectid) {
        ClientObjectDAO clientObjectDAO = new ClientObjectDAO();
        clientObjectDAO.delete(objectid);
        for(int i = 0; i < clientList.size(); ++i) {
            if(clientList.get(i).getId().equals(objectid)) {
                clientList.remove(i);
                break;
            }
        }
    }
    @RequestMapping(value = "/observe/{id}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    String observe(@PathVariable String id,
                        @PathVariable String instanceid,
                        @PathVariable String resourceid) {
        notifyBoolean = true;
        notify(id, instanceid, resourceid);
        return "hello";
    }

    @RequestMapping(value = "/notify/{id}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    String notify(String id, String instanceid, String resourceid) {
        if(checkAttribute(id, instanceid, resourceid) && notifyBoolean) {
            String url = "http://localhost:8080/server/notify";
            try {
                String json = new ObjectMapper().writeValueAsString(buildNotifyInfo(id, instanceid, resourceid));
                com.springapp.mvc.server.HttpOperation.post(url, json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "hello";
            }
        }
        return "hello";
    }
    private NotifyInfo buildNotifyInfo(String id, String instanceid, String resourceid) {

        ClientObject clientObject = clientObjectDAO.get(id);
        ClientInstance clientInstance = clientObject.getInstances().get(Integer.parseInt(instanceid));
        ClientResource clientResource = clientInstance.getResources().get(Integer.parseInt((resourceid)));
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setId((id));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        notifyInfo.setTimestamp(sdf.format(cal.getTime()));
        notifyInfo.setTemp(clientResource.getResource());
        return notifyInfo;
    }
    @RequestMapping(value = "/cancelobserve/{id}", method = RequestMethod.GET)
    public void cancelObserve(@PathVariable String id) {
        notifyBoolean = false;
    }

    private boolean checkAttribute(String id, String instanceid, String resourceid) {
        return true;
    }

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello Client!");
		return "hello";
	}
}