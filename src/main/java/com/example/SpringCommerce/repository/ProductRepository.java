package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:category IS NULL OR p.category.name = :category) AND " +
            "(:priceMin IS NULL OR p.price >= :priceMin) AND " +
            "(:priceMax IS NULL OR p.price <= :priceMax) AND " +
            "(:brand IS NULL OR p.studio.name = :brand) AND " +
            "(:color IS NULL OR p.genre.name = :color)")
    List<Product> findProductsByFilters(
            @Param("category") String category,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax,
            @Param("brand") String brand,
            @Param("color") String color
    );
}