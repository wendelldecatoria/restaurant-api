package com.wendecator.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {
    @Id
    private Long id;
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
}