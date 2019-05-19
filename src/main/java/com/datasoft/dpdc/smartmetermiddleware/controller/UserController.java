package com.datasoft.dpdc.smartmetermiddleware.controller;

import com.datasoft.dpdc.smartmetermiddleware.model.User;
import com.datasoft.dpdc.smartmetermiddleware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by rayhan on 9/19/18.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("add") User user, HttpServletRequest request, Model model) {
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            if (userService.addUser(user)) {
                model.addAttribute("success", "User Added Successfully");
            }  else {
                model.addAttribute("error", "User Add Failed");
            }
        }
        return "user/user_add";
    }

    @RequestMapping("/list")
    public String getMeterList(Model model) {

        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        return "user/user_list";
    }

}
