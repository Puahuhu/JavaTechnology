package com.example.Ex6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
//            studentRepository.saveAll(Arrays.asList(
//                    new Student(null, "Nguyen Van A", 20, "nva@example.com", 6.5),
//                    new Student(null, "Le Thi B", 22, "ltb@example.com", 7.0),
//                    new Student(null, "Tran Van C", 19, "tvc@example.com", 5.5),
//                    new Student(null, "Pham Thi D", 21, "ptd@example.com", 6.0),
//                    new Student(null, "Do Van E", 20, "dve@example.com", 5.0),
//                    new Student(null, "Bui Thi F", 22, "btf@example.com", 8.0),
//                    new Student(null, "Hoang Van G", 18, "hvg@example.com", 7.5),
//                    new Student(null, "Tran Thi H", 23, "tth@example.com", 6.0),
//                    new Student(null, "Phan Van I", 20, "pvi@example.com", 5.5),
//                    new Student(null, "Ngo Thi J", 19, "ntj@example.com", 6.5)
//            ));

            Sort sort = Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore"));
            Iterable<Student> sortedStudents = studentRepository.findAll(sort);
            System.out.println("Danh sach sinh vien co do tuoi giam dan, IELTS tang dan:");
            sortedStudents.forEach(System.out::println);

            Pageable pageable = PageRequest.of(1, 3, Sort.by("age").descending());
            Page<Student> pagedStudents = studentRepository.findAll(pageable);
            System.out.println("Sinh vien trang 2:");
            pagedStudents.forEach(System.out::println);
        };
    }
}
