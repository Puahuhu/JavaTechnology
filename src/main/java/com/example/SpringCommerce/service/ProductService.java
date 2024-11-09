package com.example.SpringCommerce.service;

import com.example.SpringCommerce.model.Product;
import com.example.SpringCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> filterProducts(String category, Double priceMin, Double priceMax, String brand, String color) {
        return productRepository.findProductsByFilters(
                category != null && !category.isEmpty() ? category : null,
                priceMin,
                priceMax,
                brand != null && !brand.isEmpty() ? brand : null,
                color != null && !color.isEmpty() ? color : null
        );
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}