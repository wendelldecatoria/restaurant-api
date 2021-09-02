package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.CategoryDTO;
import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.models.Category;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/categories")
    public ResponseEntity<Object> getAllCategories() {
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", categoryList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/categories/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategory(id).orElseThrow(() -> new Exception("No category with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", category);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/categories")
    public ResponseEntity<Object> addCategory(@Valid @DTO(CategoryDTO.class) Category category) {
        try {
            Category saveCategory = categoryService.saveCategory(category);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Category created successfully", saveCategory);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}")
    public ResponseEntity<Object> updateCategory(
            @Valid @DTO(CategoryDTO.class) Category category,
            @RequestParam Long id) {
        try {
            categoryService.getCategory(id).orElseThrow(() -> new Exception("No category with id " + id + " found."));
            Category saveCategory = categoryService.saveCategory(category);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Category updated successfully", saveCategory);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/categories/{id}")
    public ResponseEntity<Object> patchCategory(
            @Valid @DTO(CategoryDTO.class) Category category,
            @RequestParam Long id) {
        try {
            categoryService.getCategory(id).orElseThrow(() -> new Exception("No category with id " + id + " found."));
            Category saveCategory = categoryService.saveCategory(category);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Category updated successfully", saveCategory);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        try {
            Category getCategory = categoryService.getCategory(id).orElseThrow(() -> new Exception("No category with id " + id + " found."));
            categoryService.deleteCategory(getCategory);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Category deleted successfully", null);
        } catch (Exception e ) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
