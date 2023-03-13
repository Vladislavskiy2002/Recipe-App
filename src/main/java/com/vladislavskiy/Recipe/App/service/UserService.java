package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(final Integer id);
    List<User> getAllUsers();
    void addUser(User user);
    User getByEmail(final String email);
    User getAuthUser();
}
