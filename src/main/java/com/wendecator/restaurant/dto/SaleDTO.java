package com.wendecator.restaurant.dto;

import com.wendecator.restaurant.models.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaleDTO {
    @Id
    private Long id;

    @NotNull(message = "order is required")
    private Order order;

    // TODO: throw error for @Min and @Max
    @NotNull(message = "Discount is required")
    @Min(value = 0, message = "Discount should not be less than 0.01")
    @Max(value = 1, message = "Discount should not be greater than 1")
    private Integer discount;
}
