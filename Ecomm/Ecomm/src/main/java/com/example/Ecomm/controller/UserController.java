package com.example.Ecomm.controller;


import com.example.Ecomm.model.User;
import com.example.Ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public User registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginuser(@RequestBody User user)
    {
        return  userService.loginUser(user.getEmail(),user.getPassword());
    }

    @GetMapping("/all-user")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
