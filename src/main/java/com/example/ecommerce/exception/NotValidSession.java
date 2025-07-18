package com.example.ecommerce.exception;

public class NotValidSession extends RuntimeException {
    public NotValidSession(String message) {
        super(message);
    }
}
