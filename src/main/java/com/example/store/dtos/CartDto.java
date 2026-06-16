package com.example.store.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class CartDto {
  private UUID id;
  private List<CartItemDto> items = new ArrayList<>();

  @JsonProperty("totalPrice")
  public BigDecimal getTotalPrice() {
    return items.stream().map(CartItemDto::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
