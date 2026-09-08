package com.example.demo.exception;

public class DataNotExists extends RuntimeException {
    public DataNotExists(String message) {
        super(message);
    }
}
