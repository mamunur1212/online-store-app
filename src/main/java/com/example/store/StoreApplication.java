package com.example.store;


import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.Profile;
import com.example.store.entities.Tag;
import com.example.store.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
	
	var repository =  context.getBean(UserRepository.class);

	// SAVE — id is null, so Spring does an INSERT and the DB generates the id
	User user = new User("Mamun", "mamun@example.com", "secret");
	repository.save(user);
	Long id = user.getId();
	System.out.println("Saved with id = " + id);

	// FIND by id
	Optional<User> result = repository.findById(id);
	System.out.println("Found: " + result.orElse(null));

	// DELETE by id
	repository.deleteById(id);
	System.out.println("Exists after delete? " + repository.existsById(id));

}

}
