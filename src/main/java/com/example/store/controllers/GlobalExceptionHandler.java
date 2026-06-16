package com.example.store.controllers;

import com.example.store.dtos.ErrorDto;
import com.example.store.exceptions.CartNotFoundException;
import com.example.store.exceptions.ProductNotFoundException;
import com.example.store.exceptions.ProductNotInCartException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({CartNotFoundException.class, ProductNotInCartException.class})
  public ResponseEntity<ErrorDto> handleNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorDto> handleProductNotFound(ProductNotFoundException exception) {
    return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorDto> handleTypeMismatch(
      MethodArgumentTypeMismatchException exception) {
    var error =
        "Invalid value '" + exception.getValue() + "' for parameter '" + exception.getName() + "'.";
    return ResponseEntity.badRequest().body(new ErrorDto(error));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationErrors(
      MethodArgumentNotValidException exception) {
    var errors = new HashMap<String, String>();

    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              errors.put(error.getField(), error.getDefaultMessage());
            });

    return ResponseEntity.badRequest().body(errors);
  }
}
