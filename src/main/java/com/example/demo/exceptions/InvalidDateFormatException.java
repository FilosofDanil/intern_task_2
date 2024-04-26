package com.example.demo.exceptions;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException() {
    }

    public InvalidDateFormatException(String message) {
        super(message);
    }

    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateFormatException(Throwable cause) {
        super(cause);
    }
}
