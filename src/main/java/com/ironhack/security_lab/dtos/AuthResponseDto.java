package com.ironhack.security_lab.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponseDto {
    private String token;
    private String username;
    private List<String> roles;
}

