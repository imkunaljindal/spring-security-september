package com.example.springsecurityseptember.controller;

import com.example.springsecurityseptember.dao.StudentRepository;
import com.example.springsecurityseptember.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to ADMIN area!!!";
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add/username/{username}/password/{password}")
    public Student addStudent(@PathVariable("username") String username,
                              @PathVariable("password") String password){

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setRole("ROLE_ADMIN");

        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }
}
