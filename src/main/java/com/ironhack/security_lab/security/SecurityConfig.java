package com.ironhack.security_lab.security;

import com.ironhack.security_lab.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// we let Spring know that this class is a configuration class
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    // we let Spring know that this method is a bean
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(csrf -> csrf.disable()) // For development. In production, configure appropriately
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Server doesn't store session state, because it's a REST API
               .authorizeHttpRequests(auth -> auth
                       // Public routes
                       .requestMatchers("/api/auth/**").permitAll()
                       .requestMatchers("/api/public/**").permitAll()
                       // Routes protected by role
                       .requestMatchers("/api/admin").hasRole("ADMIN")
                       // All other routes require authentication
                       .anyRequest().authenticated()
               )
               // Add our filter before UsernamePasswordAuthenticationFilter
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
}