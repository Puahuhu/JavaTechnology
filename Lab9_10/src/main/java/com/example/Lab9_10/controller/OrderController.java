package com.example.Lab9_10.controller;

import com.example.Lab9_10.model.Order;
import com.example.Lab9_10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<com.example.Lab9_10.model.Order> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    // Other methods for GET, PUT, DELETE by ID
}
