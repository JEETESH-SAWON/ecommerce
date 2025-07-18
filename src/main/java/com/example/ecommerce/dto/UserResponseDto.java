package com.example.ecommerce.dto;


import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String userRole = "user";
    private Long userId;
    private String userName;
    private String userPassword;
}
