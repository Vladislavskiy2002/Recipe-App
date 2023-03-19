package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SignInController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/signIn")
    public String getSignIn()
    {
        log.info("METHOD public String getSignIn()");
        return "signIn_page";
    }
}
