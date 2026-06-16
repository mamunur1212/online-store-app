package com.example.store.controllers;

import com.example.store.dtos.AddItemToCartRequest;
import com.example.store.dtos.CartDto;
import com.example.store.dtos.CartItemDto;
import com.example.store.dtos.UpdateCartItemRequest;
import com.example.store.services.CartService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
  private final CartService cartService;

  @PostMapping
  public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
    var cartDto = cartService.createCart();
    var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
    return ResponseEntity.created(uri).body(cartDto);
  }

  @GetMapping
  public ResponseEntity<List<CartDto>> getAllCarts() {
    return ResponseEntity.ok(cartService.getAllCarts());
  }

  @GetMapping("/{cartId}")
  public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
    return ResponseEntity.ok(cartService.getCart(cartId));
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<CartItemDto> addToCart(
      @PathVariable UUID cartId, @Valid @RequestBody AddItemToCartRequest request) {
    var cartItem = cartService.addToCart(cartId, request.getProductId());
    return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
  }

  @PutMapping("/{cartId}/items/{productId}")
  public ResponseEntity<CartItemDto> updateItem(
      @PathVariable UUID cartId,
      @PathVariable Long productId,
      @Valid @RequestBody UpdateCartItemRequest request) {
    var cartItem = cartService.updateItem(cartId, productId, request.getQuantity());
    return ResponseEntity.ok(cartItem);
  }

  @DeleteMapping("/{cartId}/items/{productId}")
  public ResponseEntity<?> removeItem(@PathVariable UUID cartId, @PathVariable Long productId) {
    cartService.removeItem(cartId, productId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}/items")
  public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
    cartService.clearCart(cartId);
    return ResponseEntity.noContent().build();
  }
}
