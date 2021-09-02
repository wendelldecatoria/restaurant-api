package com.wendecator.restaurant.services;

import com.wendecator.restaurant.models.ApiUser;
import com.wendecator.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<ApiUser> getAllUsers() {
        List<ApiUser> apiUsers = new ArrayList<>();
        userRepository.findAll().forEach(apiUsers::add);
        return apiUsers;
    }

    public Optional<ApiUser> getUser(Long id) {
        Optional<ApiUser> user = userRepository.findById(id);
        return user;
    }

    public ApiUser getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public ApiUser addUser(ApiUser apiUser) {
        return userRepository.save(apiUser);
    }

    public void deleteUser(ApiUser apiUser) {
        userRepository.delete(apiUser);
    }
}
