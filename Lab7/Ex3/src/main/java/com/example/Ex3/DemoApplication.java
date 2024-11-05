package com.example.Ex3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

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
            studentRepository.save(new Student(null, "Nguyen Van A", 20, "144@example.com", 7.5));
            studentRepository.save(new Student(null, "Nguyen Thi B", 22, "125@example.com", 6.5));
            studentRepository.save(new Student(null, "ABC", 23, "126@example.com", 5.0));
            studentRepository.save(new Student(null, "123", 23, "446@example.com", 6.0));
            studentRepository.save(new Student(null, "ABC", 23, "5343@example.com", 5.5));
            studentRepository.save(new Student(null, "ABC", 23, "34314@example.com", 7.0));

            studentRepository.findAll().forEach(System.out::println);
        };
    }
}