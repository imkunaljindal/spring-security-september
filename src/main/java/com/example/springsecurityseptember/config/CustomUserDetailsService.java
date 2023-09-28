package com.example.springsecurityseptember.config;

import com.example.springsecurityseptember.dao.StudentRepository;
import com.example.springsecurityseptember.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentRepository.findByUsername(username);
        if(student==null){
            throw new UsernameNotFoundException("Invalid username");
        }

        return new UserDetailsCreator(student);
    }
}
