package com.example.store.controllers;

import com.example.store.dtos.CreateProductRequest;
import com.example.store.dtos.ProductDto;
import com.example.store.dtos.UpdateProductRequest;
import com.example.store.entities.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.repositories.CategoryRepository;
import com.example.store.repositories.ProductRepository;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CategoryRepository categoryRepository;

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

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(
      @RequestBody CreateProductRequest request, UriComponentsBuilder uriBuilder) {
    var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
    if (category == null) {
      return ResponseEntity.badRequest().build();
    }
    Product product = productMapper.toEntity(request);
    product.setCategory(category);
    productRepository.save(product);

    var productDto = productMapper.toDto(product);
    var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

    return ResponseEntity.created(uri).body(productDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(
      @PathVariable Long id, @RequestBody UpdateProductRequest request) {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }

    productMapper.update(request, product);

    if (request.getCategoryId() != null) {
      var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
      if (category == null) {
        return ResponseEntity.badRequest().build();
      }
      product.setCategory(category);
    }

    productRepository.save(product);

    return ResponseEntity.ok(productMapper.toDto(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }

    productRepository.delete(product);
    return ResponseEntity.noContent().build();
  }
}
