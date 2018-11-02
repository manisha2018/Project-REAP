package com.projectreap.ProjectReap.controller;

import com.projectreap.ProjectReap.entity.User;
import com.projectreap.ProjectReap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showHome(User user) {
        return "reap";
    }

    /* Saved data of user registered in DB*/
    @PostMapping("/usersubmit")
     public ModelAndView createUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView=new ModelAndView();
        User userExists= userService.findByUsername(user.getUserName());

        if(userExists!=null){
            bindingResult.rejectValue("userName","error.user","This Username already exists!");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("reap");
        }
        else{
            userService.update(user);
            modelAndView.setViewName("success");
        }
        return modelAndView;
    }

}
