package com.projectreap.ProjectReap.controller;

import com.projectreap.ProjectReap.entity.User;
import com.projectreap.ProjectReap.enums.Role;
import com.projectreap.ProjectReap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
//@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;


/*Start Page for login and register*/
    @GetMapping(value = {"/", "/index"})
    public String showHome(User user) {
        return "index";
    }

    /*Logout Functionality*/
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index?act=lo";
    }

    /* Saved data of user registered in DB*/
    @PostMapping("/")
    public ModelAndView createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUsername(user.getUserName());

        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "This Username already exists!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
        } else {
            userService.update(user);
            modelAndView.addObject("message","User Registered Successfully!!");
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @PostMapping("/login")
    public String handleLogin(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute User user, Model model, HttpSession session) {

        User loggedInUser = userService.findByUsername(user.getUserName());
        if (loggedInUser == null) {
            model.addAttribute("msg", "Invalid User");
            return "redirect:/";
        } else {
            if (!loggedInUser.getPassword().isEmpty() && loggedInUser.getPassword().equals(user.getPassword())) {
//                request.getSession().setAttribute("user",user);
                if (loggedInUser.getRole() == Role.USER) {
                    //add user detail in session(assign session to logged in user)
                    addUserInSession(loggedInUser, session);
                    return "redirect:/user/dashboard";
                } else {
                    //add user detail in session(assign session to logged in user)
                    addUserInSession(loggedInUser, session);
                    return "redirect:/admin/dashboard";
                }
            } else {
                model.addAttribute("msg", "Error,Login Failed! Enter Valid Credentials");
                return "redirect:/";
            }
        }

    }

    @GetMapping("/user/dashboard")
    public ModelAndView getUserDashboard(HttpServletRequest request, Model model) {

        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser.getRole() == Role.USER) {
            return new ModelAndView("userDashboard");
        } else {
            return new ModelAndView().addObject("message", "User Not Exists");
        }
    }

    @GetMapping("/admin/dashboard")
    public ModelAndView getAdminDashboard(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser.getRole() == Role.ADMIN) {

            return new ModelAndView("adminDashboard");
        } else {
            return new ModelAndView().addObject("message", "User Not Exists");
        }

    }

    private void addUserInSession(User u, HttpSession httpSession) {
        httpSession.setAttribute("user", u);
        httpSession.setAttribute("id", u.getId());
        httpSession.setAttribute("role", u.getRole());
    }
}
