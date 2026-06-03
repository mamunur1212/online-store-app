package com.example.store;


import com.example.store.entities.Address;
import com.example.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);

		User user = new User(1L, "Mamun", "mamun@example.com", "secret");

		Address address = new Address(1L, "123 Main St", "Dhaka", "1207", "Dhaka");
		user.addAddress(address);

		System.out.println(user);
		System.out.println(user.getAddresses());
	}

}
