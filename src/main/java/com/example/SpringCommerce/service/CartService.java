package com.example.SpringCommerce.service;

import com.example.SpringCommerce.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@SessionScope
public class CartService {

    private final Map<Product, Integer> cart = new HashMap<>();

    public void addProductToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    public void addProductToCart(Product product) {
        addProductToCart(product, 1);
    }

    public Map<Product, Integer> getCartItems() {
        return cart;
    }

    public int getTotalQuantity() {
        return cart.values().stream().mapToInt(quantity -> quantity).sum();
    }

    public double getTotalPrice() {
        return cart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public String getFormattedTotalPrice() {
        double totalPrice = getTotalPrice();
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(totalPrice) + " đ";
    }

    public void clearCart() {
        cart.clear();
    }
}