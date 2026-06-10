package com.example.store.repositories;

import com.example.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	@EntityGraph(attributePaths = {"profile", "tags"})
	Optional<User> findByEmail(String email);
	
	@EntityGraph(attributePaths = "addresses")
	@Query("select u from User u")
	List<User> findAllWithTags();
}