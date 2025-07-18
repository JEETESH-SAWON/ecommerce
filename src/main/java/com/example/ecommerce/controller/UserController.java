package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute UserRequestDto dto, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorList", result.getAllErrors());
            return "login";
        }
        return userService.userValidate(dto, session);
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute UserRequestDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorList", result.getAllErrors());
            return "redirect:/register";
        }
        return userService.addUser(dto);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear all session data
        return "redirect:/login"; // or homepage
    }

}
