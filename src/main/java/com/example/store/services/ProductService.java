package com.example.store.services;

import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public void createProduct() {
		Category category = new Category("Book");

		Product product = new Product("My books1", BigDecimal.valueOf(19.99));
		product.setCategory(category);

		// CascadeType.PERSIST on Product.category cascade-persists the new
		// category when the product is saved.
		productRepository.save(product);
	}
}
