package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
