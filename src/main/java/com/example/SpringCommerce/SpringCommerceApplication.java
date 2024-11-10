package com.example.SpringCommerce;

import com.example.SpringCommerce.model.*;
import com.example.SpringCommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductRepository productRepository, AccountRepository accountRepository,
							 CategoryRepository categoryRepository, StudioRepository studioRepository, GenreRepository genreRepository) {
		return args -> {
			Category electronics = categoryRepository.save(new Category(1, "Electronics"));
			Category clothing = categoryRepository.save(new Category(2,"Clothing"));
			Category furniture = categoryRepository.save(new Category(3, "Furniture"));

			Studio brandA = studioRepository.save(new Studio(1, "Brand A"));
			Studio brandB = studioRepository.save(new Studio(2, "Brand B"));
			Studio brandC = studioRepository.save(new Studio(3, "Brand C"));

			Genre red = genreRepository.save(new Genre(1, "Red"));
			Genre blue = genreRepository.save(new Genre(2, "Blue"));
			Genre green = genreRepository.save(new Genre(3, "Green"));
			Genre black = genreRepository.save(new Genre(4, "Black"));
			Genre yellow = genreRepository.save(new Genre(5, "Yellow"));

			productRepository.save(new Product(1, "Product 1", 100.0, electronics, brandA, red));
			productRepository.save(new Product(2, "Product 2", 150.0, clothing, brandB, blue));
			productRepository.save(new Product(3, "Product 3", 200.0, electronics, brandA, black));
			productRepository.save(new Product(4, "Product 4", 250.0, clothing, brandC, red));
			productRepository.save(new Product(5, "Product 5", 300.0, electronics, brandB, green));
			productRepository.save(new Product(6, "Product 6", 350.0, furniture, brandC, yellow));

			accountRepository.save(new Account(1, "user1", "123"));
			accountRepository.save(new Account(2, "user2", "123"));
		};
	}
}
