package com.example.springsecurityseptember.dao;

import com.example.springsecurityseptember.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByUsername(String username);
}
