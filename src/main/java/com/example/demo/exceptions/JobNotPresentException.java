package com.example.demo.exceptions;

public class JobNotPresentException extends RuntimeException{
    public JobNotPresentException() {
    }

    public JobNotPresentException(String message) {
        super(message);
    }

    public JobNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNotPresentException(Throwable cause) {
        super(cause);
    }
}
