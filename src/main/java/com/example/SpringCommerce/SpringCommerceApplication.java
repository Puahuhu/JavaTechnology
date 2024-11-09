package com.example.SpringCommerce;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.model.Brand;
import com.example.SpringCommerce.model.Category;
import com.example.SpringCommerce.model.Color;
import com.example.SpringCommerce.model.Product;
import com.example.SpringCommerce.repository.AccountRepository;
import com.example.SpringCommerce.repository.BrandRepository;
import com.example.SpringCommerce.repository.CategoryRepository;
import com.example.SpringCommerce.repository.ColorRepository;
import com.example.SpringCommerce.repository.ProductRepository;
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
							 CategoryRepository categoryRepository, BrandRepository brandRepository, ColorRepository colorRepository) {
		return args -> {
			Category electronics = categoryRepository.save(new Category(1, "Electronics"));
			Category clothing = categoryRepository.save(new Category(2,"Clothing"));
			Category furniture = categoryRepository.save(new Category(3, "Furniture"));

			Brand brandA = brandRepository.save(new Brand(1, "Brand A"));
			Brand brandB = brandRepository.save(new Brand(2, "Brand B"));
			Brand brandC = brandRepository.save(new Brand(3, "Brand C"));

			Color red = colorRepository.save(new Color(1, "Red"));
			Color blue = colorRepository.save(new Color(2, "Blue"));
			Color green = colorRepository.save(new Color(3, "Green"));
			Color black = colorRepository.save(new Color(4, "Black"));
			Color yellow = colorRepository.save(new Color(5, "Yellow"));

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
