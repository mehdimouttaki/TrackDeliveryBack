package com.trackdelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        // Handle your custom exception with BAD_REQUEST (400)
        @ExceptionHandler(OrderModificationException.class)
        public ResponseEntity<ApiError> handleOrderModificationException(OrderModificationException ex) {
            ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // Handle generic exceptions with INTERNAL_SERVER_ERROR (500)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            // optionally log ex here for debugging
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }



}
