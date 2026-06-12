package com.example.store.repositories;

import com.example.store.entities.Product;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @EntityGraph(attributePaths = {"category"})
  List<Product> findByCategoryId(Byte categoryId, Sort sort);
}
