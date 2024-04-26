package com.example.demo.exceptions;

public class NoContentPresentException extends RuntimeException{
    public NoContentPresentException() {
    }

    public NoContentPresentException(String message) {
        super(message);
    }

    public NoContentPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentPresentException(Throwable cause) {
        super(cause);
    }
}
