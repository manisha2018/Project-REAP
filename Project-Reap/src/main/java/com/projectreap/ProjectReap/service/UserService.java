package com.projectreap.ProjectReap.service;

import com.projectreap.ProjectReap.entity.User;
import com.projectreap.ProjectReap.entity.UserMapper;
import com.projectreap.ProjectReap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class UserService {

    public static final String ROLE_ADMIN = "Admin";


    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HttpSession httpSession;

    public User update(User user) {
        Iterator<User> iterator = userRepository.findAll().iterator();
        if (iterator.hasNext()) {
            userRepository.save(user);
        }
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.getUsername(username);
    }

    /*
    * Takes the Username and password from database.*/
   /* public User login(String username,String password){
        String sql="SELECT id,firstName,lastName,userName,email,role FROM User WHERE userName=:un AND password=:pwd ";
        Map m=new HashMap();
        m.put("un",username);
        m.put("pwd",password);
        User u=jdbcTemplate.queryForObject(sql,m,new UserMapper());
        return u;
    }*/

}
