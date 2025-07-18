package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.model.OrderEntity;
import com.example.ecommerce.model.OrderItemEntity;
import com.example.ecommerce.reposatory.OrderItemRepository;
import com.example.ecommerce.reposatory.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final HttpSession session;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CartService cartService, HttpSession session) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
        this.session = session;
    }

    public List<OrderEntity> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public void placeOrder() {
        Long userId = (Long) session.getAttribute("userId");
        List<CartResponseDto> cartItems = cartService.getCartItems(userId);

        if (cartItems == null || cartItems.isEmpty()) return;

        double total = 0;
        for (CartResponseDto item : cartItems) {
            total += item.getProductPrice() * item.getQuantity();
        }

        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setOrderTime(LocalDateTime.now());
        order = orderRepository.save(order);

        for (CartResponseDto item : cartItems) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName());
            orderItem.setPrice(item.getProductPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSubtotal(item.getProductPrice() * item.getQuantity());
            orderItemRepository.save(orderItem);
        }

        cartService.clearCart(userId);
    }
}

