package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.dto.MenuDTO;
import com.wendecator.restaurant.models.Menu;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

// TODO: setup swagger annotations
// TODO: create custom exceptions

@RestController
@Validated
@RequestMapping("api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menus")
    public ResponseEntity<Object> getAllMenus() {
        try {
            List<Menu> menuList = menuService.getAllMenus();
            return RespondSuccess.generateResponse(HttpStatus.OK, true,"OK", menuList);
        } catch(Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/menus/{id}")
    public ResponseEntity<Object> getMenu(@PathVariable Long id) {
        try {
            Menu getMenu = menuService.getMenu(id).orElseThrow(() -> new Exception("No menu with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", getMenu);
        } catch(Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/menus")
    public ResponseEntity<Object> addMenu(@Valid @DTO(MenuDTO.class) Menu menu) {
        try {
            Menu saveMenu = menuService.saveMenu(menu);
            return RespondSuccess.generateResponse(HttpStatus.OK, true,"Menu created successfully", saveMenu);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/menus/{id}")
    public ResponseEntity<Object> updateMenu(
            @Valid @DTO(MenuDTO.class) Menu menu,
            @PathVariable Long id) {
        try {
            menuService.getMenu(id).orElseThrow(() -> new Exception("No menu with id " + id + " found."));
            Menu saveMenu = menuService.saveMenu(menu);
            return RespondSuccess.generateResponse(HttpStatus.OK, true,"Menu updated successfully", saveMenu);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/menus/{id}")
    public ResponseEntity<Object> patchMenu(
            @Valid @DTO(MenuDTO.class) Menu menu,
            @PathVariable Long id) {
        try {
            menuService.getMenu(id).orElseThrow(() -> new Exception("No menu with id " + id + " found."));
            Menu saveMenu = menuService.saveMenu(menu);
            return RespondSuccess.generateResponse(HttpStatus.OK, true,"Menu updated successfully", saveMenu);
        } catch (Exception e ) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/menus/{id}")
    public ResponseEntity<Object> deleteMenu(@PathVariable Long id) {
        try {
            Menu getMenu = menuService.getMenu(id).orElseThrow(() -> new Exception("No menu with id " + id + " found."));
            menuService.deleteMenu(getMenu);
            return RespondSuccess.generateResponse(HttpStatus.OK, true,"Menu deleted successfully", null);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
