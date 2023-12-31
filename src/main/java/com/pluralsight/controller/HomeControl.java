package com.pluralsight.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControl {
    @GetMapping("/")
    public String homePage(@RequestParam(required = false) String country) {
        if (country != null && !country.isEmpty()) {
            return "Hello " + country + "!";
        } else {
            return "Hello World!";
        }
    }
}