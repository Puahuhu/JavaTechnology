package com.example.lab9_10.service;

import com.example.lab9_10.entity.Order;
import com.example.lab9_10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrderNumber(updatedOrder.getOrderNumber());
        order.setTotalSellingPrice(updatedOrder.getTotalSellingPrice());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
