package com.datasoft.dpdc.smartmetermiddleware.controller;

import com.datasoft.dpdc.smartmetermiddleware.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome to Smart Meter Backoffice");
        return "home/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Welcome to Smart Meter Backoffice");
        return "login/login";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        model.addAttribute("title", "Welcome to Smart Meter Backoffice");
        return "home/home";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error(Model model) {
        model.addAttribute("title", "ACCESS DENIED");
        return "error/403";
    }
}
