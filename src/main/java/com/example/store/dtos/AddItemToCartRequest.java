package com.example.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {
  @NotNull(message = "ProductId is required")
  private Long productId;
}
