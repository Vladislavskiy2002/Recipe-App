package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.RoleRepository;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
@Slf4j
public class SignUpController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/signUp")
    public String getSignUp() {
        log.info("METHOD public String getSignUp()");
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            System.out.println("EMAIL: " + user.getEmail() + " is already exist"); /// TODO: 11.03.2023 зробити так щоб це висвічувало у формі червоним кольором 
            return "redirect:/signUp";
        }
        user.setHashPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        // user.setEnabled(true); todo: зробити поле enable після того як очищу базу данних
        userRepository.save(user);
        return "redirect:/mvc/user";
    }
}
