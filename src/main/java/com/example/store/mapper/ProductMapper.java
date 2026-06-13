package com.example.store.mapper;

import com.example.store.dtos.CreateProductRequest;
import com.example.store.dtos.ProductDto;
import com.example.store.dtos.UpdateProductRequest;
import com.example.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(target = "categoryId", source = "category.id")
  ProductDto toDto(Product product);

  @Mapping(target = "category", ignore = true)
  Product toEntity(CreateProductRequest request);

  @Mapping(target = "category", ignore = true)
  void update(UpdateProductRequest request, @MappingTarget Product product);
}
