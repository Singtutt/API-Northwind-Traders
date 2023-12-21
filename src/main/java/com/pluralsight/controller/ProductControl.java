package com.pluralsight.controller;

import com.pluralsight.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductControl {
    private final List<Product> products = new ArrayList<>();

    public ProductControl() {
        products.add(new Product(1, 1, "Product 1", 100.00));
        products.add(new Product(2, 2, "Product 2", 200.00));
        products.add(new Product(3, 3, "Product 3", 300.00));
        products.add(new Product(4, 4, "Product 4", 400.00));
    }
    @GetMapping("/products")
    public List<Product> allProducts(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) Integer categoryID,
                                     @RequestParam(required = false) Double price) {
        List<Product> productsFilter = new ArrayList<>(products);
        if (name != null) {
            productsFilter.removeIf(product -> !product.getProductName().equalsIgnoreCase(name));
        }
        if (categoryID != null) {
            productsFilter.removeIf(product -> product.getCategoryID() != categoryID);
        }
        if (price != null) {
            productsFilter.removeIf(product -> product.getUnitPrice() != price);
        }
        return productsFilter;
    }
    @GetMapping("/products/{id}")
    public Product byProductID(@PathVariable int id) {
        for(Product product : products) {
            if (product.getProductID() == id) {
                return product;
            }
        }
        return null;
    }
}
