package com.springapp.mvc.server;

import com.springapp.mvc.client.bootstrap.Bootstrap;
import com.springapp.mvc.server.bootstrap.ServerBootstrapDAO;
import com.springapp.mvc.server.register.RegisterInfo;
import com.springapp.mvc.server.register.ServerRegisterInfoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WU on 14/11/2015.
 */
@Controller
@RequestMapping("/server")
public class ServerController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello Server!");

        return "hello";
    }
    @RequestMapping(value = "/bootstrap/{id}", method = RequestMethod.GET)
    public @ResponseBody Bootstrap getBootstrap(@PathVariable String id) {
        ServerBootstrapDAO serverBootstrapDAO = new ServerBootstrapDAO();
        return serverBootstrapDAO.getBootstrap(id);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void clientRegister(@RequestBody RegisterInfo registerInfo) {
        ServerRegisterInfoDAO serverRegisterInfoDAO = new ServerRegisterInfoDAO();
        serverRegisterInfoDAO.save(registerInfo);
    }
    @RequestMapping(value = "/register/{id}", method = RequestMethod.DELETE)
    public void clientDeregister(@PathVariable String id) {
        ServerRegisterInfoDAO serverRegisterInfoDAO = new ServerRegisterInfoDAO();
        serverRegisterInfoDAO.delete(id);
    }

    //this method for testing only
    @RequestMapping(value = "/register/{id}", method = RequestMethod.GET)
    public String getRegisterInfo(@PathVariable String id, ModelMap model) {
        ServerRegisterInfoDAO serverRegisterInfoDAO = new ServerRegisterInfoDAO();
        String registerInfo = serverRegisterInfoDAO.getRegisterInfo(id);

        model.addAttribute("message", registerInfo == null ? "null" : registerInfo);
        return "hello";
    }

}
