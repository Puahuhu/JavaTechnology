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
			Category online = categoryRepository.save(new Category("Game Online"));
			Category offline = categoryRepository.save(new Category("Game Offline"));

			Studio fromSoftware = studioRepository.save(new Studio("From Software "));
			Studio gameScience = studioRepository.save(new Studio("Game Science"));
			Studio ubisoft = studioRepository.save(new Studio("Ubisoft"));

			Genre hanhDong = genreRepository.save(new Genre("Hành động"));
			Genre kinhDi = genreRepository.save(new Genre("Kinh dị"));
			Genre soul = genreRepository.save(new Genre("Soul"));

			productRepository.save(new Product("Elden Ring", 100.0, online, fromSoftware, soul));
			productRepository.save(new Product("Black Myth Wukong", 150.0, offline, gameScience, hanhDong));
			productRepository.save(new Product("Assassin's Creed: Origins", 250.0, offline, ubisoft, hanhDong));
			productRepository.save(new Product("Assassin's Creed Odyssey", 300.0, online, ubisoft, hanhDong));
			productRepository.save(new Product("Assassin's Creed Valhalla", 350.0, offline, ubisoft, hanhDong));

			accountRepository.save(new Account(1, "user1", "123"));
			accountRepository.save(new Account(2, "user2", "123"));
		};
	}
}
