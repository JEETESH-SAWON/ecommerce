package com.example.ecommerce.reposatory;

import com.example.ecommerce.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartEntity c WHERE c.userId = :userId AND c.productId = :productId")
    void deleteByUserIdAndProductId(Long userId, Long productId);
}
