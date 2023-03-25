package com.vladislavskiy.Recipe.App.security.details;

import com.vladislavskiy.Recipe.App.entity.Privilege;
import com.vladislavskiy.Recipe.App.entity.Role;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.RoleRepository;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import com.vladislavskiy.Recipe.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

//@Controller(value = "customUserDetailsService")
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//   @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if(user!= null)
//            return new UserDetailsImpl(user);
//        else
//            throw new UsernameNotFoundException("User not found");
//    }
//}
@Service(value = "customUserDetailsService")
@Transactional
public class UserDetailsServiceImpl extends UserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private RoleRepository roleRepository;

    public UserDetailsServiceImpl() {
        super(new User());
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Collections.singletonList(
                            roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getHashPassword(), true, true, true,
                true, getAuthorities(user.getRoles())); //todo: в третю колонку вставити user.getIsEnabled
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}