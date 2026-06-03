package com.example.store;


import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.Profile;
import com.example.store.entities.Tag;
import com.example.store.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);

		User user = new User(1L, "Mamun", "mamun@example.com", "secret");

		Address address = new Address(1L, "123 Main St", "Dhaka", "1207", "Dhaka");
		user.addAddress(address);

		Tag vip = new Tag(1, "vip");
		Tag earlyAdopter = new Tag(2, "early-adopter");
		user.addTag(vip);
		user.addTag(earlyAdopter);

		Profile profile = new Profile(null, "Loves coding", "01700000000",
				LocalDate.of(1995, 1, 1), 100);
		user.setProfile(profile);

		Category category = new Category((byte) 1, "Electronics");
		Product phone = new Product(1L, "Phone", new BigDecimal("499.99"));
		Product laptop = new Product(2L, "Laptop", new BigDecimal("1299.00"));
		category.addProduct(phone);
		category.addProduct(laptop);

		user.addProduct(phone);
		user.addProduct(laptop);

		System.out.println(user);
		System.out.println(user.getAddresses());
		System.out.println(user.getTags());
		System.out.println(user.getProfile());
		System.out.println(category);
		System.out.println(category.getProducts());
		System.out.println(user.getProducts());
	}

}
