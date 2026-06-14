package com.example.store.controllers;

import com.example.store.dtos.AddItemToCartRequest;
import com.example.store.dtos.CartDto;
import com.example.store.dtos.ErrorDto;
import com.example.store.dtos.UpdateCartItemRequest;
import com.example.store.entities.Cart;
import com.example.store.mapper.CartMapper;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.ProductRepository;
import jakarta.validation.Valid;
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
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final CartMapper cartMapper;

  @PostMapping
  public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
    Cart cart = new Cart();
    cartRepository.save(cart);

    var cartDto = cartMapper.toDto(cart);
    var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

    return ResponseEntity.created(uri).body(cartDto);
  }

  @GetMapping("/{cartId}")
  public ResponseEntity<?> getCart(@PathVariable UUID cartId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Cart not found."));
    }

    return ResponseEntity.ok(cartMapper.toDto(cart));
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<?> addToCart(
      @PathVariable UUID cartId, @Valid @RequestBody AddItemToCartRequest request) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Cart not found."));
    }

    var product = productRepository.findById(request.getProductId()).orElse(null);
    if (product == null) {
      return ResponseEntity.badRequest().body(new ErrorDto("Product not found."));
    }

    var cartItem = cart.addItem(product);
    cartRepository.save(cart);

    return ResponseEntity.status(HttpStatus.CREATED).body(cartMapper.toDto(cartItem));
  }

  @PutMapping("/{cartId}/items/{productId}")
  public ResponseEntity<?> updateItem(
      @PathVariable UUID cartId,
      @PathVariable Long productId,
      @Valid @RequestBody UpdateCartItemRequest request) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Cart not found."));
    }

    var cartItem = cart.getItem(productId);
    if (cartItem == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ErrorDto("Product was not found in the cart."));
    }

    cartItem.setQuantity(request.getQuantity());
    cartRepository.save(cart);

    return ResponseEntity.ok(cartMapper.toDto(cartItem));
  }

  @DeleteMapping("/{cartId}/items/{productId}")
  public ResponseEntity<?> removeItem(@PathVariable UUID cartId, @PathVariable Long productId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Cart not found."));
    }

    var cartItem = cart.getItem(productId);
    if (cartItem == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ErrorDto("Product was not found in the cart."));
    }

    cart.removeItem(cartItem);
    cartRepository.save(cart);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}/items")
  public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Cart not found."));
    }

    cart.clear();
    cartRepository.save(cart);

    return ResponseEntity.noContent().build();
  }
}
