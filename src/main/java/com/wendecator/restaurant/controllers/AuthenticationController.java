package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.dto.UserDTO;
import com.wendecator.restaurant.models.ApiUser;
import com.wendecator.restaurant.models.AuthenticationRequest;
import com.wendecator.restaurant.models.AuthenticationResponse;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.ApiUserDetailsService;
import com.wendecator.restaurant.services.UserService;
import com.wendecator.restaurant.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ApiUserDetailsService apiUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Object> addUser(@Valid @DTO(UserDTO.class) ApiUser apiUser) {
        try {
            apiUser.setPassword(this.encoder().encode(apiUser.getPassword()));
            ApiUser saveApiUser = userService.addUser(apiUser);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "User successfully created", saveApiUser);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = apiUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
