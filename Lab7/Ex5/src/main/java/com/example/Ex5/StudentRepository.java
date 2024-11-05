package com.example.Ex5;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findStudentsByAge(@Param("age") int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore = :ieltsScore")
    long countStudentsByIeltsScore(@Param("ieltsScore") double ieltsScore);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchStudentsByName(@Param("keyword") String keyword);
}
