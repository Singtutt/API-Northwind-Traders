package com.pluralsight.controller;

import com.pluralsight.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryControl {
    private final List<Category> categories =  new ArrayList<>();

    public CategoryControl() {
        categories.add(new Category(1, "Category 1"));
        categories.add(new Category(2, "Category 2"));
        categories.add(new Category(3, "Category 3"));
        categories.add(new Category(4, "Category 4"));
    }
    @GetMapping("/categories")
    public List<Category> allCategories(@RequestParam(required = false) String name) {
        List<Category> categoryFilter =  new ArrayList<>(categories);
        if(name != null) {
            categoryFilter.removeIf(category -> !category.getCategoryName().equalsIgnoreCase(name));
        }
        return categoryFilter;
    }
    @GetMapping("/categories/{id}")
    public Category byCategoryID(@PathVariable int id) {
        for (Category category : categories) {
            if (category.getCategoryID() == id) {
                return category;
            }
        }
        return null;
    }
}
