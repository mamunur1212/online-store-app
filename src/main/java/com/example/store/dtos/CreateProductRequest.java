package com.example.store.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateProductRequest {
  private String name;
  private String description;
  private BigDecimal price;
  private Byte categoryId;
}
