package com.example.Lab9_10.repository;

import com.example.Lab9_10.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
