package com.example.Lab9_10.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String productName;
    private Double price;
    private String illustration;
    private String description;

    // Getters and setters
}
