package com.vladislavskiy.Recipe.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/mvc")
public class UserMVC {
    @GetMapping("/user")
    public String getUser()
    {
        return "users_page";
    }
}
