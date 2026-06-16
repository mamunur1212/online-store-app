package com.example.store.exceptions;

public class ProductNotInCartException extends RuntimeException {
  public ProductNotInCartException() {
    super("Product was not found in the cart.");
  }
}
