package com.example.cv.exception;

public class LevelSkillNotFoundException extends RuntimeException {
    public LevelSkillNotFoundException(String message) {
        super(message);
    }
}