package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double subtotal;

}

