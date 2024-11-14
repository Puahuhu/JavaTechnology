package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByAccount(Account account);
}