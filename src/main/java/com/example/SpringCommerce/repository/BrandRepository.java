package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
