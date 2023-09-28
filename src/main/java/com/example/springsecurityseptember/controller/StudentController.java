package com.example.springsecurityseptember.controller;

import com.example.springsecurityseptember.dao.StudentRepository;
import com.example.springsecurityseptember.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to STUDENT area!!!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello STUDENT";
    }

    @PostMapping("/add/username/{username}/password/{password}")
    public Student addStudent(@PathVariable("username") String username,
                      @PathVariable("password") String password){

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setRole("ROLE_STUDENT,ROLE_RANDOM");

        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }
}
