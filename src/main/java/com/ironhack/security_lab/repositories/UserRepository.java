package com.ironhack.security_lab.repositories;


import com.ironhack.security_lab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// repositories/UserRepository.java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

