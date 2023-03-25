package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(final Integer id);

    List<User> getAllUsers();

    void addUser(User user);

    User getByEmail(final String email);

    User getAuthUser();

    void createUserWithRoles(String username, String password, List<String> roleNames);

    void deleteUser(User user);

    List<User> findAllByName(final String name);
}
