package com.example.stockx.exception;

public class InvalidRequestException extends Throwable {
    public InvalidRequestException(String message) {
        super(message);
    }
}
