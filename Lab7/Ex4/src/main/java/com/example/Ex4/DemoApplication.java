package com.example.Ex4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            studentRepository.saveAll(Arrays.asList(
                    new Student(null, "Nguyen Van A", 20, "nva@example.com", 6.5),
                    new Student(null, "Le Thi B", 22, "ltb@example.com", 7.0),
                    new Student(null, "Tran Van C", 19, "tvc@example.com", 5.5),
                    new Student(null, "Pham Thi D", 21, "ptd@example.com", 6.0)
            ));

            int age = 20;
            List<Student> studentsByAge = studentRepository.findByAgeGreaterThanEqual(age);
            System.out.println("Sinh vien co do tuoi >= " + age + ": " + studentsByAge);

            double ieltsScore = 6.0;
            long countIeltsScore = studentRepository.countByIeltsScore(ieltsScore);
            System.out.println("So sinh vien co diem IELTS = " + ieltsScore + ": " + countIeltsScore);

            String keyword = "an";
            List<Student> studentsByName = studentRepository.findByNameContainingIgnoreCase(keyword);
            System.out.println("Sinh vien co ten chua tu khoa '" + keyword + "': " + studentsByName);
        };
    }
}
