package com.example.store.services;

import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.CategoryRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
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

	@Transactional
	public void createProductForExistingCategory() {
		// Retrieve the category created earlier (id is a Byte).
		Category category = categoryRepository.findById((byte) 1).orElseThrow();

		Product product = new Product("My books2", BigDecimal.valueOf(29.99));
		product.setCategory(category);

		productRepository.save(product);
	}

	@Transactional
	public void addProductsToWishlist() {
		// Fetch an existing user.
		User user = userRepository.findById(1L).orElseThrow();

		// Add all existing products to the user's wishlist.
		for (Product product : productRepository.findAll()) {
			user.addProduct(product);
		}

		// User owns the wishlist join table, so saving the user writes the rows.
		userRepository.save(user);
	}

	@Transactional
	public void deleteProduct() {
		// The wishlist FK on product_id is ON DELETE CASCADE, so the DB clears
		// any wishlist rows for this product when it is removed.
		productRepository.deleteById(2L);
	}

	@Transactional
	public void queryExamples() {
		// ---- String derived queries ----
		// exact match: WHERE name = 'Laptop'
		productRepository.findByName("Laptop");
		// LIKE: caller supplies the wildcards -> WHERE name LIKE '%Lap%'
		productRepository.findByNameLike("%Lap%");
		// NOT LIKE -> WHERE name NOT LIKE '%Lap%'
		productRepository.findByNameNotLike("%Lap%");
		// CONTAINING: Spring wraps the value -> WHERE name LIKE '%Lap%'
		productRepository.findByNameContaining("Lap");
		// STARTING WITH -> WHERE name LIKE 'Lap%'
		productRepository.findByNameStartingWith("Lap");
		// ENDING WITH -> WHERE name LIKE '%top'
		productRepository.findByNameEndingWith("top");
		// case-insensitive ENDING WITH -> WHERE lower(name) LIKE '%top'
		productRepository.findByNameEndingWithIgnoreCase("TOP");

		// ---- Number derived queries ----
		productRepository.findByPrice(new BigDecimal("19.99"));                 // price = 19.99
		productRepository.findByPriceGreaterThan(new BigDecimal("20"));         // price > 20
		productRepository.findByPriceGreaterThanEqual(new BigDecimal("20"));    // price >= 20
		productRepository.findByPriceLessThanEqual(new BigDecimal("100"));      // price <= 100
		productRepository.findByPriceBetween(new BigDecimal("10"), new BigDecimal("50")); // BETWEEN 10 AND 50

		// ---- Null checks ----
		productRepository.findByDescriptionNull();      // description IS NULL
		productRepository.findByDescriptionNotNull();   // description IS NOT NULL

		// ---- Multiple conditions (AND) ----
		// description IS NULL AND name IS NULL — name is NOT NULL in the schema,
		// so this always returns empty; it just shows the And keyword.
		productRepository.findByDescriptionNullAndNameNull();

		// ---- Sorting (OrderBy) ----
		productRepository.findByNameOrderByPrice("Laptop"); // WHERE name = ? ORDER BY price ASC

		// ---- Limiting (Top / First) ----
		productRepository.findTop5ByNameOrderByPrice("Laptop");        // first 5, ordered by price
		productRepository.findFirst5ByNameLikeOrderByPrice("%Lap%");   // first 5 LIKE, ordered by price

		// ---- Custom @Query (JPQL) ----
		List<Product> ranged = productRepository.findProducts(new BigDecimal("10"), new BigDecimal("50"));
		System.out.println("findProducts -> " + ranged.size() + " rows");

		long count = productRepository.countProducts(new BigDecimal("10"), new BigDecimal("50"));
		System.out.println("countProducts -> " + count);

		// ---- Modifying @Query (bulk update) ----
		// Sets price = 9.99 for every product in category 1. Needs a transaction.
		productRepository.updatePriceByCategory(new BigDecimal("9.99"), (byte) 1);
	}
	
	@Transactional
	public void fetchProducts() {
		// var product = productRepository.findByCategory(categoryRepository.findById((byte) 1).orElseThrow());
		var product = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(15));
		System.out.println("Products in category 1: " + product);
	}
}
