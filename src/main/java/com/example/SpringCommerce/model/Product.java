package com.example.SpringCommerce.model;

import jakarta.persistence.*;

import java.text.NumberFormat;
import java.util.Locale;

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

    public String getFormattedPrice() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(price) + " đ";
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setImg(String img) {
        this.img = img;
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
