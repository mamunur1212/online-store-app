package com.example.store.repositories;

import com.example.store.dtos.ProductSummary;
import com.example.store.dtos.ProductSummaryDTO;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	//String
	List<Product> findByName(String name);
	List<Product> findByNameLike(String name);
	List<Product> findByNameNotLike(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByNameStartingWith(String name);
	List<Product> findByNameEndingWith(String name);
	List<Product> findByNameEndingWithIgnoreCase(String name);

	// Numbers
	List<Product> findByPrice(BigDecimal price);
	List<Product> findByPriceGreaterThan(BigDecimal price);
	List<Product> findByPriceGreaterThanEqual(BigDecimal price);
	List<Product> findByPriceLessThanEqual(BigDecimal price);
	List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

	// Null
	List<Product> findByDescriptionNull();
	List<Product> findByDescriptionNotNull();

	// Multiple conditions
	List<Product> findByDescriptionNullAndNameNull();
	
	// Sort (OrderBy)
	List<Product> findByNameOrderByPrice(String name);
	
	// Limit (Top/First)
	List<Product> findTop5ByNameOrderByPrice(String name);
	List<Product> findFirst5ByNameLikeOrderByPrice(String name);

	// Find products whose prices are in a given range and sort by name
	// SQL or JPQL
	// @Query("select p from Product p join p.category where p.price between :min and :max order by p.name")
	@Procedure("findProductsByPrice")
	List<Product> findProducts(BigDecimal min, BigDecimal max);
	// List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
	
	@Query("select count(*) from Product p where p.price between :min and :max")
	long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
	
	@Modifying
	@Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
	void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);
	
	// List<Product> findByCategory(Category category);
	// List<ProductSummary> findByCategory(Category category);

	
	List<ProductSummaryDTO> findByCategory(Category category);
}
