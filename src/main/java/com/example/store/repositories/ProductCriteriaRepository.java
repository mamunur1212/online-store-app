package com.example.store.repositories;

import com.example.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
	List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
