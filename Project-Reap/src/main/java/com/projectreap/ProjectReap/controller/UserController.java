package com.projectreap.ProjectReap.controller;

import com.projectreap.ProjectReap.entity.User;
import com.projectreap.ProjectReap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showHome(User user) {
        return "reap";
    }

//
//    @GetMapping("/users")
//    public User saveUser(@ModelAttribute("user") User user){
//        return userService.update(user);
//    }
//

    /* Saved data of user registered in DB*/
    @PostMapping("/usersubmit")
    @ResponseBody
    public String getEmployeedetails(@ModelAttribute("user") User user) {
        userService.update(user);
        System.out.println(user);
        return "Hello User";
    }
}
