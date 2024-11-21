package com.example.lab9_10;

import com.example.lab9_10.entity.Order;
import com.example.lab9_10.entity.UserAccount;
import com.example.lab9_10.entity.Product;
import com.example.lab9_10.repository.OrderRepository;
import com.example.lab9_10.repository.UserRepository;
import com.example.lab9_10.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class Lab910Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Lab910Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new UserAccount(
                "user1@example.com",
                passwordEncoder.encode("password1"),
                "John",
                "Doe"
        ));
        userRepository.save(new UserAccount(
                "user2@example.com",
                passwordEncoder.encode("password2"),
                "Jane",
                "Smith"
        ));

        productRepository.save(new Product("P001", "Laptop", 1500.00, "laptop.jpg", "A high-quality laptop."));
        productRepository.save(new Product("P002", "Phone", 800.00, "phone.jpg", "A latest smartphone."));

        orderRepository.save(new Order("ORD001", 2300.00, Arrays.asList("P001", "P002")));
        orderRepository.save(new Order("ORD002", 1500.00, Arrays.asList("P001")));
    }
}
