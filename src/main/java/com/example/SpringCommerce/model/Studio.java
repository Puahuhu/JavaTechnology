package com.example.SpringCommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Studio {

    @Id
    private int id;
    private String name;

    public Studio() {}

    public Studio(int id, String name) {
        this.id = id;
        this.name = name;
    }

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