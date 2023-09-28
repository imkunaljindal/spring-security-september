package com.example.springsecurityseptember.config;

import org.apache.coyote.http11.HttpOutputBuffer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**")
                .permitAll()
                .requestMatchers("/student/add/**")
                .permitAll()
                .requestMatchers("/student/welcome")
                .hasAnyRole("STUDENT","ADMIN")
                .requestMatchers("/admin/add/**")
                .permitAll()
                .requestMatchers("/admin/welcome")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){

//        UserDetails student = User.withUsername("Salik")
//                .password(getPasswordEncoder().encode("salik123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails student1 = User.withUsername("Shriniwas")
//                .password(getPasswordEncoder().encode("shriniwas123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails admin = User.withUsername("Arif")
//                .password(getPasswordEncoder().encode("arif123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(student,admin,student1);

        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
