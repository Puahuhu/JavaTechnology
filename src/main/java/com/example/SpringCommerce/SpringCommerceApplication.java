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
			Studio hitchcockGames = studioRepository.save(new Studio("Hitchcock Games"));
			Studio supermassiveGames = studioRepository.save(new Studio("Supermassive Games"));

			Genre hanhDong = genreRepository.save(new Genre("Hành động"));
			Genre kinhDi = genreRepository.save(new Genre("Kinh dị"));
			Genre soul = genreRepository.save(new Genre("Soul"));

			productRepository.save(new Product("Elden Ring", 990000, online, fromSoftware, soul, "images/elden-ring.png"));
			productRepository.save(new Product("Black Myth Wukong", 990000, offline, gameScience, hanhDong, "images/wukong.png"));
			productRepository.save(new Product("Assassin's Creed: Origins", 990000, offline, ubisoft, hanhDong, "images/ac_origins.jpg"));
			productRepository.save(new Product("Assassin's Creed Odyssey", 990000, offline, ubisoft, hanhDong, "images/ac_odyssey.jpg"));
			productRepository.save(new Product("Assassin's Creed Valhalla", 990000, offline, ubisoft, hanhDong, "images/ac_valhalla.jpg"));
			productRepository.save(new Product("Pacify", 73000, online, hitchcockGames, kinhDi, "images/pacify.jpg"));
			productRepository.save(new Product("The Dark Pictures Anthology - Man of Medan", 348000, offline, supermassiveGames, kinhDi, "images/man_of_medan.jpg"));
			productRepository.save(new Product("The Dark Pictures Anthology: Little Hope", 348000, offline, supermassiveGames, kinhDi, "images/little_hope.jpg"));
			productRepository.save(new Product("The Dark Pictures Anthology: House of Ashes", 350000, offline, supermassiveGames, kinhDi, "images/house_of_ashes.jpg"));
			productRepository.save(new Product("The Dark Pictures Anthology: The Devil in Me", 480000, offline, supermassiveGames, kinhDi, "images/the_evil_in_me.jpg"));

			accountRepository.save(new Account(1, "user1", "123"));
			accountRepository.save(new Account(2, "user2", "123"));
		};
	}
}
