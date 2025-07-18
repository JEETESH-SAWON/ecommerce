package com.example.ecommerce.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginException(LoginFailedException ex, Model model) {
        model.addAttribute("loginError", ex.getMessage());
        return "redirect:/login";
    }

    @ExceptionHandler(NotValidSession.class)
    public String handelNotValidSession(NotValidSession ex, Model model) {
        model.addAttribute("loginError", ex.getMessage());
        return "redirect/login";
    }
}