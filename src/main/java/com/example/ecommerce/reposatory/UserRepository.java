package com.example.ecommerce.reposatory;

import com.example.ecommerce.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String username);
}
