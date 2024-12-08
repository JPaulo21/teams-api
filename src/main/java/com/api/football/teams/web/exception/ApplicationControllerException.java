package com.api.football.teams.web.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}
