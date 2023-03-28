package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.Role;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/mvc")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("roles", roles);
        model.addAttribute("User", user);
        return "users";
    }

    @GetMapping("/admin/allUsers")
    public String allUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("Users", users);
        return "all-users";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUser(User idUser) {
        Optional<User> user = userService.getUserById(idUser.getId());
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            return "redirect:/mvc/admin/allUsers";
        } else {
            throw new RuntimeException("OBJECT USER IS NULL!!!");
        }
    }
}
