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

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public synchronized void notifyListener(@RequestBody NotifyInfo notifyInfo) {
        serverDAO.saveNotifyInfo(notifyInfo);
    }

}
