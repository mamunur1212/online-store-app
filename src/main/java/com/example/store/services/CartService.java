package com.example.store.services;

import com.example.store.dtos.CartDto;
import com.example.store.dtos.CartItemDto;
import com.example.store.entities.Cart;
import com.example.store.exceptions.CartNotFoundException;
import com.example.store.exceptions.ProductNotFoundException;
import com.example.store.exceptions.ProductNotInCartException;
import com.example.store.mapper.CartMapper;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final CartMapper cartMapper;

  public CartDto createCart() {
    var cart = new Cart();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public List<CartDto> getAllCarts() {
    return cartRepository.findAll().stream().map(cartMapper::toDto).toList();
  }

  public CartDto getCart(UUID cartId) {
    var cart = getCartOrThrow(cartId);
    return cartMapper.toDto(cart);
  }

  public CartItemDto addToCart(UUID cartId, Long productId) {
    var cart = getCartOrThrow(cartId);

    var product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

    var cartItem = cart.addItem(product);
    cartRepository.save(cart);

    return cartMapper.toDto(cartItem);
  }

  public CartItemDto updateItem(UUID cartId, Long productId, Integer quantity) {
    var cart = getCartOrThrow(cartId);

    var cartItem = cart.getItem(productId);
    if (cartItem == null) {
      throw new ProductNotInCartException();
    }

    cartItem.setQuantity(quantity);
    cartRepository.save(cart);

    return cartMapper.toDto(cartItem);
  }

  public void removeItem(UUID cartId, Long productId) {
    var cart = getCartOrThrow(cartId);

    var cartItem = cart.getItem(productId);
    if (cartItem == null) {
      throw new ProductNotInCartException();
    }

    cart.removeItem(cartItem);
    cartRepository.save(cart);
  }

  public void clearCart(UUID cartId) {
    var cart = getCartOrThrow(cartId);
    cart.clear();
    cartRepository.save(cart);
  }

  private Cart getCartOrThrow(UUID cartId) {
    return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
  }
}
