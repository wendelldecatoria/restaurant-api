package com.wendecator.restaurant.dto;

import com.wendecator.restaurant.models.Menu;
import com.wendecator.restaurant.models.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemDTO {
    @Id
    private Long id;
    @NotNull(message = "order is required")
    private Order order;
    @NotNull(message = "menu is required")
    private Menu menu;
}
