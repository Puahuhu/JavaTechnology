package com.example.Lab9_10;

import com.example.Lab9_10.model.User;
import com.example.Lab9_10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab910Application implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Lab910Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Tạo dữ liệu mẫu cho người dùng
		User user1 = new User();
		user1.setEmail("user1@example.com");
		user1.setPassword("password123"); // Mật khẩu sẽ được mã hóa trong UserService
		user1.setFirstName("John");
		user1.setLastName("Doe");

		User user2 = new User();
		user2.setEmail("user2@example.com");
		user2.setPassword("password456");
		user2.setFirstName("Jane");
		user2.setLastName("Smith");

		// Thêm người dùng vào cơ sở dữ liệu
		userService.register(user1);
		userService.register(user2);

		System.out.println("Sample users added to the database.");
	}
}
