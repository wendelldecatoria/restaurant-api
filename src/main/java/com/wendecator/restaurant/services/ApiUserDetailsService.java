package com.wendecator.restaurant.services;

import com.wendecator.restaurant.models.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser apiUser = userService.getUserByName(username);
        if (apiUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(apiUser.getUsername(), apiUser.getPassword(), new ArrayList<>());
    }
}
