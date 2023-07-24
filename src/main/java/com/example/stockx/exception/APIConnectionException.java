package com.example.stockx.exception;

public class APIConnectionException extends RuntimeException {
    public APIConnectionException(String s) {
        super(s);
    }
}
