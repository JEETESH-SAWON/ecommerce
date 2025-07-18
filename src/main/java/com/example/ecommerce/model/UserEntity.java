package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false, length = 100)
    private String userPassword;
    @Column(nullable = false)
    private String userRole = "user";

}