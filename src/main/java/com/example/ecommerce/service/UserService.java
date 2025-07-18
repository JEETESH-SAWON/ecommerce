package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.exception.LoginFailedException;
import com.example.ecommerce.model.UserEntity;
import com.example.ecommerce.reposatory.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepo;

    UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public String userValidate(UserRequestDto dto, HttpSession session) {

        UserEntity user = userRepo.findByUserName(dto.getUserName());
        if (user == null || !user.getUserPassword().equals(dto.getUserPassword())) {
            throw new LoginFailedException("Invalid username or password");
        } else {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userRole", user.getUserRole());
            return "redirect:/product";
        }

    }

    public String addUser(UserRequestDto dto) {
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        user.setUserPassword(dto.getUserPassword());
        userRepo.save(user);
        return "login";
    }
}
