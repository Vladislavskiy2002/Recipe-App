package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import com.vladislavskiy.Recipe.App.security.details.UserDetailsImpl;
import com.vladislavskiy.Recipe.App.security.details.UserDetailsServiceImpl;
import com.vladislavskiy.Recipe.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller("/mvc")
public class UserMVC {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String getUser(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetailsImpl) {
            username = ((UserDetailsImpl)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(username);
        User user = userService.getByEmail(username);
        model.addAttribute("User", user);
        return "users_page";
    }
}
