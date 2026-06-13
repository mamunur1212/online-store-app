package com.example.store.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateProductRequest {
  private String name;
  private String description;
  private BigDecimal price;
  private Byte categoryId;
}
