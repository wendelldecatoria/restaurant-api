package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.dto.MenuListDTO;
import com.wendecator.restaurant.dto.OrderDTO;
import com.wendecator.restaurant.models.Item;
import com.wendecator.restaurant.models.Menu;
import com.wendecator.restaurant.models.MenuList;
import com.wendecator.restaurant.models.Order;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.ItemService;
import com.wendecator.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// TODO: setup swagger annotations
// TODO: create custom exceptions

@RestController
@Validated
@RequestMapping("api")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;

    @RequestMapping("/orders")
    public ResponseEntity<Object> getAllOrders() {
        try {
            List<Order> orderList = orderService.getAllOrders();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", orderList);
        } catch (Exception e ) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/orders/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        try {
            Order getOrder = orderService.getOrder(id).orElseThrow(() -> new Exception("No order with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", getOrder);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    public ResponseEntity saveOrder(@Valid @DTO(MenuListDTO.class) MenuList menuList) {
        try {
            Order order = new Order();
            Order saveOrder = orderService.saveOrder(order);

            List<Menu> menus = menuList.getMenuList();

            for (Menu menu : menus) {
                Item item = new Item(saveOrder, menu);
                itemService.saveItem(item);
            }

            Order getOrder = orderService.getOrder(saveOrder.getId()).orElseThrow(() -> new Exception("No order with id " + saveOrder.getId() + " found."));
            // TODO: need to return list of items for order

            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Order created successfully", getOrder);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}")
    public ResponseEntity updateOrder(
            @Valid @DTO(OrderDTO.class) Order order,
            @PathVariable Long id) {
        try {
            orderService.getOrder(id).orElseThrow(() -> new Exception("No order with id " + id + " found."));
            Order saveOrder = orderService.saveOrder(order);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Order updated successfully", saveOrder);
        } catch (Exception e ) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/orders/{id}")
    public ResponseEntity patchOrder(
            @Valid @DTO(OrderDTO.class) Order order,
            @PathVariable Long id) {
        try {
            orderService.getOrder(id).orElseThrow(() -> new Exception("No order with id " + id + " found."));
            Order saveOrder = orderService.saveOrder(order);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Order updated successfully", saveOrder);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/orders/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            Order getOrder = orderService.getOrder(id).orElseThrow(() -> new Exception("No order with id " + id + " found."));
            orderService.deleteOrder(getOrder);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Order deleted successfully", null);
        } catch (Exception e ) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
