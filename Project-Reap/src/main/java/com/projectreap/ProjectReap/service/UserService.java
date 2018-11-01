package com.projectreap.ProjectReap.service;

import com.projectreap.ProjectReap.entity.User;
import com.projectreap.ProjectReap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User update(User user) {
        Iterator<User> iterator = userRepository.findAll().iterator();
        if (!iterator.hasNext()) {
            userRepository.save(user);
        } else {
            userRepository.save(user);
        }
        return user;
    }
}
