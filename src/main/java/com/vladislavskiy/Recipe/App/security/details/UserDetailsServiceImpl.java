package com.vladislavskiy.Recipe.App.security.details;

import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Controller(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user!= null)
            return new UserDetailsImpl(user);
        else
            throw new UsernameNotFoundException("User not found");
    }
}
