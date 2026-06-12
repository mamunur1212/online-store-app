package com.example.store.controllers;

import com.example.store.dtos.ProductDto;
import com.example.store.entities.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.repositories.ProductRepository;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @GetMapping
  public List<ProductDto> getProducts(
      @RequestParam(required = false, name = "categoryId") Byte categoryId,
      @RequestParam(required = false, defaultValue = "", name = "sortBy") String sortBy) {
    if (!Set.of("id", "name", "price").contains(sortBy)) {
      sortBy = "name";
    }
    Sort sort = Sort.by(sortBy);
    List<Product> products =
        categoryId != null
            ? productRepository.findByCategoryId(categoryId, sort)
            : productRepository.findAll(sort);
    return products.stream().map(productMapper::toDto).toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(productMapper.toDto(product));
  }
}
