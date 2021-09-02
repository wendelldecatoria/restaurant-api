package com.wendecator.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDTO {

    @Id
    private Long id;
    @NotNull(message = "Title is required")
    private String title;
}
