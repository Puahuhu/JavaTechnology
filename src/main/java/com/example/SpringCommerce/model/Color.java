package com.example.SpringCommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Color {

    @Id
    private int id;
    private String name;

    // Constructors
    public Color() {}

    public Color(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}