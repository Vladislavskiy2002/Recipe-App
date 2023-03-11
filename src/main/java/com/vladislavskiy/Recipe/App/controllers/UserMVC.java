package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller("/mvc")
public class UserMVC {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user")
    public String getUser(Model model)
    {
        User user = userRepository.getById(2);
        model.addAttribute("User", user);
        return "users_page";
    }
}
