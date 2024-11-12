package com.example.SpringCommerce.model;

import jakarta.persistence.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;
    private double totalPrice;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    public OrderDetail() {
        this.orderDate = new Date();
    }

    public OrderDetail(Order order, Product product, int quantity, double totalPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getFormattedTotalPrice() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(totalPrice) + " đ";
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getFormattedOrderDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(orderDate);
    }
}