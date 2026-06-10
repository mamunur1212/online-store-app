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
import com.example.store.services.ProductService;
import com.example.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
	
	var service =  context.getBean(ProductService.class);

	service.fetchProducts();

	}

}
