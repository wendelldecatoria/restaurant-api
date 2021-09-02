package com.wendecator.restaurant.dto;

import com.wendecator.restaurant.models.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class MenuListDTO {

    @NotNull(message = "Menu list is required")
    private List<Menu> menuList;
}
