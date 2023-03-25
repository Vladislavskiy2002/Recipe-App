package com.vladislavskiy.Recipe.App.service.impl;

import com.vladislavskiy.Recipe.App.entity.Role;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;
import com.vladislavskiy.Recipe.App.repository.RoleRepository;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import com.vladislavskiy.Recipe.App.security.details.UserDetailsImpl;
import com.vladislavskiy.Recipe.App.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public Optional<User> getUserById(final Integer id) {
        return userRepository.findById(id);
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
        return userRepository.findByEmail(email);
    }

    @Override
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createUserWithRoles(String username, String password, List<String> roleNames) {
        User user = new User();
        user.setName(username);
        user.setHashPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByName(roleNames.get(0));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
        userRepository.delete(user);
    }

    @Override
    public List<User> findAllByName(final String name) {
        return userRepository.findByName(name);
    }
}
