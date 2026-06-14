package com.example.store.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CartItemDto {
  private CartProductDto product;
  private Integer quantity;

  @JsonProperty("totalPrice")
  public BigDecimal getTotalPrice() {
    return product.getPrice().multiply(BigDecimal.valueOf(quantity));
  }
}
