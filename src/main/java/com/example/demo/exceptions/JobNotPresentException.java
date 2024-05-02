package com.example.demo.exceptions;

public class JobNotPresentException extends RuntimeException{
    public JobNotPresentException(String message) {
        super(message);
    }
}
