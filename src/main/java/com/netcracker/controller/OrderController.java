package com.netcracker.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/orderID")
    public String hello(@RequestParam(value = "orderID", defaultValue = "?") String name) {
        return String.format("Hello %s!", name);
    }
}