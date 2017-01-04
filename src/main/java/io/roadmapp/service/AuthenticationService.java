package io.roadmapp.service;

import io.roadmapp.model.CurrentUserDetails;
import io.roadmapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserService userService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("========== fetch current user by email ==========");
        User user = userService.getUser(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", email));
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        System.out.println(user.getId());
        UserDetails userDetails =
                new CurrentUserDetails(
                        user.getEmail(),
                        user.getPassword(),
                        Arrays.asList(authority),
                        user.getId()
                );
        System.out.println(userDetails);
        return userDetails;
    }

}