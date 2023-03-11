package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    {
        System.out.println("+++++USER CONTROLLER HAS BEEN STARTED+++++");
    }
    @Autowired
    private UserService userService;
    @GetMapping( "/all")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user) //todo: change on dto
    {
         userService.addUser(user);
    }
}
