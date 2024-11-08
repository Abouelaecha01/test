package com.example.cv.exception;

public class HobbyNotFoundException extends RuntimeException {
    public HobbyNotFoundException(String message) {
        super(message);
    }
}
