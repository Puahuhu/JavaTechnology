package com.example.SpringCommerce.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String img;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Studio studio;

    @ManyToOne
    private Genre genre;

    public Product() {}

    public Product(String name, double price, Category category, Studio studio, Genre genre, String img) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.studio = studio;
        this.genre = genre;
        this.img = img;
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

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Studio getStudio() {
        return studio;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getImg() {
        return img;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    public int hashCode() {
        return id;
    }
}
