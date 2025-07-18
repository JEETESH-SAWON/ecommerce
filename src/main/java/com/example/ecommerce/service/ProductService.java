package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.exception.NotValidSession;
import com.example.ecommerce.model.ProductEntity;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.reposatory.ProductRepository;
import com.example.ecommerce.reposatory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    private final UserRepository userRepository;
    private final CartService cartService;

    ProductService(ProductRepository productRepo, UserRepository userRepository, CartService cartService) {
        this.productRepo = productRepo;
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    public List<ProductResponseDto> showProduct() {
        List<ProductEntity> product = productRepo.findAll();
        return product.stream()
                .map(p -> new ProductResponseDto(p.getProductId(), p.getProductName(), p.getProductPrice(), p.getProductDescription()))
                .collect(Collectors.toList());
    }

    public String deleteProduct(Long productId) {
        Optional<ProductEntity> product = productRepo.findById(productId);
        if (product.isPresent()) {
            productRepo.deleteById(productId);
        }
        return "redirect:/product";
    }

    public void validate(Long userId) {
        Optional<UserEntity> check = userRepository.findById(userId);
        if (check.isEmpty()) {
            throw new NotValidSession("Not a valid Session");

        }

    }

    public void createProduct(ProductRequestDto dto) {
        ProductEntity product = new ProductEntity();
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductDescription(dto.getProductDescription());
        productRepo.save(product);

    }

    public int checkCartItemNo(Long userId) {
        List<CartResponseDto> list = cartService.getCartItems(userId);
        return list.stream().mapToInt(CartResponseDto::getQuantity).sum();
    }
}
