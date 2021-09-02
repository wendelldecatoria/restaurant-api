package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.dto.ItemDTO;
import com.wendecator.restaurant.models.Item;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.ItemService;
import com.wendecator.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("api")
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;

    // TODO: setup swagger annotations
    // TODO: create custom exceptions

    @RequestMapping("/orders/{orderId}/items")
    public ResponseEntity<Object> getItemsByOrderId(@PathVariable Long orderId) {
        try {
            orderService.getOrder(orderId).orElseThrow(() -> new Exception("No order with id " + orderId + " found."));
            List<Item> items = itemService.getItemsByOrderId(orderId);

            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", items);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/orders/{orderId}/items/{itemId}")
    public ResponseEntity deleteItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        try {
            orderService.getOrder(orderId).orElseThrow(() -> new Exception("No order with id " + orderId + " found."));
            Item getItem = itemService.getItem(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            itemService.deleteItem(getItem);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", null);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "//orders/{orderId}/items")
    public ResponseEntity<Object> saveItem(
            @Valid @DTO(ItemDTO.class) Item item,
            @PathVariable Long orderId) {
        // TODO: use orderId from Request Body for validation
        // TODO: enable persist of multiple menu items
        try {
            orderService.getOrder(orderId).orElseThrow(() -> new Exception("No order with id " + orderId + " found."));
            Item saveItem = itemService.saveItem(item);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Order item created successfully", saveItem);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false,  e.getMessage());
        }
    }
}
