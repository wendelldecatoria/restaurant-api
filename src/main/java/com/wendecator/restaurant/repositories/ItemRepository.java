package com.wendecator.restaurant.repositories;

import com.wendecator.restaurant.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long > {
    List<Item> findItemsByOrderId(Long orderId);
}
