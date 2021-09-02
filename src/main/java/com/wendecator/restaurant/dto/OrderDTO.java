package com.wendecator.restaurant.dto;

import com.wendecator.restaurant.models.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
public class OrderDTO {
    @Id
    private Long id;

    private Set<Item> items;
}
