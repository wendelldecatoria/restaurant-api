package com.wendecator.restaurant.services;

import com.wendecator.restaurant.models.Item;
import com.wendecator.restaurant.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    public List<Item> getItemsByOrderId(Long orderId) {
        List<Item> items = new ArrayList<>();
        return (List<Item>) itemRepository.findItemsByOrderId(orderId);
    }

    public Optional<Item> getItem(Long id) {
        return itemRepository.findById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
}
