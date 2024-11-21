package com.example.SpringCommerce.controller;

import com.example.SpringCommerce.model.Order;
import com.example.SpringCommerce.model.OrderDetail;
import com.example.SpringCommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id: " + id));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        Order existingOrder = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id: " + id));

        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}