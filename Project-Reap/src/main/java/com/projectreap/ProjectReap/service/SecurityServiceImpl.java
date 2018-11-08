package com.projectreap.ProjectReap.service;

import com.projectreap.ProjectReap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserService userService;

    @Autowired
//    AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, String password) {
        User user=userService.findByUsername(username);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
//                (user,password,user.getAuthorities());
//
//        authenticationManager.authenticate(token);
//        boolean result = token.isAuthenticate();
        return true;
    }
}
