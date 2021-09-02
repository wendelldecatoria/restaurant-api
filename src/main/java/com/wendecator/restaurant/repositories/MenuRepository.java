package com.wendecator.restaurant.repositories;

import com.wendecator.restaurant.models.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Long> {
    List<Menu> findItemsByCategoryId(Long categoryId);
}
