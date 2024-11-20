package com.example.Lab9_10.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer_order") // Rename table to avoid SQL reserved word
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Double totalSellingPrice;

    @ManyToMany
    private List<Product> products;

    // Getters and setters
}
