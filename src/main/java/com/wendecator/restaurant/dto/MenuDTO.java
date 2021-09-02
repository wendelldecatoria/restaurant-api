package com.wendecator.restaurant.dto;

import com.wendecator.restaurant.models.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MenuDTO {
    @Id
    private Long id;

    @NotNull(message = "Category is required")
    private Category category;
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Price is required")
    private Float price;
    @NotNull(message = "Availability is required")
    private Boolean available;
}
