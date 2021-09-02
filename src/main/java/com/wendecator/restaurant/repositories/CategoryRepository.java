package com.wendecator.restaurant.repositories;


import com.wendecator.restaurant.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
