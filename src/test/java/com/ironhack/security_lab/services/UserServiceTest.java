package com.ironhack.security_lab.services;

import com.ironhack.security_lab.models.ERole;
import com.ironhack.security_lab.models.Role;
import com.ironhack.security_lab.models.User;
import com.ironhack.security_lab.repositories.RoleRepository;
import com.ironhack.security_lab.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);

        roleRepository.save(role);

        ArrayList<Role> rs = new ArrayList<>();
        rs.add(role);

        user.setUsername("admin");
        user.setPassword("1234");
        user.setRoles(rs); // Assuming you have a ROLE_ADMIN role defined
        System.out.println("Initial user: " + user);
        userService.saveUser(user);
    }

    @AfterEach
    public void tearDown() {
        //userRepository.delete(user);
    }

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryption() {
        assertTrue(user.getPassword().startsWith("$2a$")); // BCrypt hashes start with $2a$
        System.out.println("Created user: " + user);
    }

//    @Test
//    @DisplayName("Password is correct")
//    public void passwordIsCorrect() {
//        boolean isCorrect = userService.checkPassword(user, "1234");
//        assertTrue(isCorrect);
//        System.out.println("Is the password correct? " + isCorrect);
//    }
}

