package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.model.CartEntity;
import com.example.ecommerce.model.ProductEntity;
import com.example.ecommerce.reposatory.CartRepository;
import com.example.ecommerce.reposatory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public void addToCart(Long userId, Long productId) {
        CartEntity existing = cartRepo.findByUserId(userId)
                .stream()
                .filter(c -> c.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
            cartRepo.save(existing);
        } else {
            CartEntity cart = new CartEntity();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(1);
            cartRepo.save(cart);
        }
    }

    public List<CartResponseDto> getCartItems(Long userId) {
        List<CartEntity> cartItems = cartRepo.findByUserId(userId);
        List<CartResponseDto> responseList = new ArrayList<>();

        for (CartEntity cart : cartItems) {
            Optional<ProductEntity> optionalProduct = productRepo.findById(cart.getProductId());
            if (optionalProduct.isPresent()) {
                ProductEntity product = optionalProduct.get();

                CartResponseDto dto = new CartResponseDto(product.getProductId(), product.getProductName()
                        , product.getProductPrice(), cart.getQuantity());

                responseList.add(dto);
            }
        }
        return responseList;

    }

    public void removeFromCart(Long userId, Long productId) {
        cartRepo.deleteByUserIdAndProductId(userId, productId);
    }

    public void clearCart(Long userId) {
        cartRepo.deleteAll(cartRepo.findByUserId(userId));
    }
}

