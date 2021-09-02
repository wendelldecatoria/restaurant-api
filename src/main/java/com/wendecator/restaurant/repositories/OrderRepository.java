package com.wendecator.restaurant.repositories;

import com.wendecator.restaurant.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
