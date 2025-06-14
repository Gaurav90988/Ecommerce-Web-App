package com.example.Ecomm.service;

import com.example.Ecomm.model.User;
import com.example.Ecomm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   // @Autowired
    // private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        try {
          //  user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);
            System.out.println("User Added to database");
            return newUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User loginUser(String email, String password) {
        // Check if user is there or not
      User user=  userRepository.findByEmail(email);
      if (user!=null && user.getPassword().equals(password)){
          return  user;
      }
      return null;  // Invalid Credentials
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

