package com.example.Ex2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            studentRepository.save(new Student(null, "Alice", 20, "alice@example.com", 7.5));
            studentRepository.save(new Student(null, "Bob", 22, "bob@example.com", 6.5));
            studentRepository.save(new Student(null, "Charlie", 23, "charlie@example.com", 8.0));

            System.out.println("All students:");
            studentRepository.findAll().forEach(System.out::println);

            Student alice = studentRepository.findById(1L).orElse(null);
            if (alice != null) {
                alice.setAge(21);
                studentRepository.save(alice);
                System.out.println("Updated student: " + alice);
            }

            studentRepository.deleteById(2L);
            System.out.println("Remaining students after deletion:");
            studentRepository.findAll().forEach(System.out::println);
        };
    }
}
