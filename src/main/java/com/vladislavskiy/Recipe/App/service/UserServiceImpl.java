package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import com.vladislavskiy.Recipe.App.security.details.UserDetailsImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserById(final Integer id) {
        return userRepository.getById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User getAuthUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetailsImpl) {
            username = ((UserDetailsImpl) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return getByEmail(username);
    }
}
