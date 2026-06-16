package com.example.store.mapper;

import com.example.store.dtos.CartDto;
import com.example.store.dtos.CartItemDto;
import com.example.store.dtos.CartProductDto;
import com.example.store.entities.Cart;
import com.example.store.entities.CartItem;
import com.example.store.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
  CartDto toDto(Cart cart);

  CartItemDto toDto(CartItem cartItem);

  CartProductDto toDto(Product product);
}
