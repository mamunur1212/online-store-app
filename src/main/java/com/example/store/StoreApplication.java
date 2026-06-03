package com.example.store;


import com.example.store.entities.Address;
import com.example.store.entities.Tag;
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

		Tag vip = new Tag(1, "vip");
		Tag earlyAdopter = new Tag(2, "early-adopter");
		user.addTag(vip);
		user.addTag(earlyAdopter);

		System.out.println(user);
		System.out.println(user.getAddresses());
		System.out.println(user.getTags());
	}

}
