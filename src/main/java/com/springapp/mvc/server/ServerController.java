package com.springapp.mvc.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.mvc.server.register.RegisterInfo;
import com.springapp.mvc.server.register.ServerNotifyinfoDAO;
import com.springapp.mvc.server.register.ServerRegisterInfoDAO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WU on 14/11/2015.
 */
@Controller
@RequestMapping("/server")
public class ServerController {
    ServerDAO serverDAO = new ServerDAO();
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello Server!");
        return "hello";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void clientRegister(@RequestBody RegisterInfo registerInfo) {
        serverDAO.saveRegisterInfo(registerInfo);
    }
    @RequestMapping(value = "/deregister/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void clientDeregister(@PathVariable String id) {
        serverDAO.deleteRegisterInfo(id);
    }

    @RequestMapping(value = "/observe/{id}/{instanceid}/{resourceid}", method = RequestMethod.GET)
    public void observe(@PathVariable String id,
                          @PathVariable String instanceid,
                          @PathVariable String resourceid) {
        ServerRegisterInfoDAO serverRegisterInfoDAO = new ServerRegisterInfoDAO();
        RegisterInfo registerInfo = serverRegisterInfoDAO.getRegisterInfo(id);
        try {
            String url = registerInfo.getClientURI();
            HttpOperation.get(url + "observe/" + id + "/" + instanceid + "/" + resourceid);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/cancelobserve/{id}", method = RequestMethod.GET)
    public void cancelObserve(@PathVariable String id) {
        ServerRegisterInfoDAO serverRegisterInfoDAO = new ServerRegisterInfoDAO();
        RegisterInfo registerInfo = serverRegisterInfoDAO.getRegisterInfo(id);
        try {
            String url = registerInfo.getClientURI();
            HttpOperation.get(url + "cancelobserve/" + id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void notifyListener(@RequestBody NotifyInfo notifyInfo) {
        try {
            ServerNotifyinfoDAO serverNotifyinfoDAO = new ServerNotifyinfoDAO();
            serverNotifyinfoDAO.save(notifyInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for testing only
    @RequestMapping(value = "/gettemperature/{id}", method = RequestMethod.GET)
    public String getTemp(ModelMap model, @PathVariable String id) {
        ServerNotifyinfoDAO serverNotifyinfoDAO = new ServerNotifyinfoDAO();
        NotifyInfo notifyInfo = serverNotifyinfoDAO.getNotifyinfo(id);
        try {
            model.put("message", new ObjectMapper().writeValueAsString(notifyInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
