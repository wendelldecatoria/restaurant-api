package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.models.ApiUser;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<ApiUser> userList = userService.getAllUsers();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", userList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(value = "/users/{username}")
    public ResponseEntity<Object> getUserByName(@PathVariable String username) {
        try {
            ApiUser user = userService.getUserByName(username);
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", user);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
