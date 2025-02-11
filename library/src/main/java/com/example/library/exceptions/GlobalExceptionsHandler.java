package com.example.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleEmailAlreadyExistException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<?> handleBookException(BookException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("booksAlreadyHas", exception.getBooksAlreadyHas());
        response.put("booksWithoutCopies", exception.getBooksWithoutCopy());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
