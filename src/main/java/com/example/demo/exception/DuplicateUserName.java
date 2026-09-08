package com.example.demo.exception;

public class DuplicateUserName extends RuntimeException {
    public DuplicateUserName(String message) {
        super(message);
    }
}
