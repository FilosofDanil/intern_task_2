package com.example.demo.exceptionhandler;

import com.example.demo.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler  extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoContentPresentException.class)
    public ResponseEntity<Object> handleNoContentPresentException(NoContentPresentException ex) {
        return buildResponseEntity(HttpStatus.NO_CONTENT, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<Object> handleInvalidDateException(InvalidDateFormatException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JobNotPresentException.class)
    public ResponseEntity<Object> handleJobNotPresentException(JobNotPresentException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Object> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }



    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder("Validation failed for: ");

        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append("; ");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }
}
