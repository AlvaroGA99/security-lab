package com.ironhack.security_lab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// we let Spring know that this class is a configuration class
@EnableWebSecurity
public class SecurityConfig {

   @Bean // we let Spring know that this method is a bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // we disable CSRF protection for simplicity)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated() // The rest of the endpoints will require authentication
                )
                .httpBasic(Customizer.withDefaults()); // this part enables the basic authentication
        return http.build(); // this method returns the SecurityFilterChain object
    }
}