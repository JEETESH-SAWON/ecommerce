package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productId;
    @Column(nullable = false, length = 100)
    private String productName;
    @Column(nullable = false)
    private double productPrice;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String productDescription;
}
