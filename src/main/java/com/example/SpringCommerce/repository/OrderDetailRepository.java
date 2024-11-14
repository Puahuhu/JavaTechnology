package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.model.Order;
import com.example.SpringCommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Order order);
}