package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.User;
import com.example.store.repositories.AddressRepository;
import com.example.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
private final UserRepository userRepository;
	private final AddressRepository addressRepository;

	public AddressService(UserRepository userRepository, AddressRepository addressRepository) {
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}
	
	public void fetchAddress() {
		Address address = addressRepository.findById(11L).orElseThrow();
		System.out.println(address);
	}
	
	public void persistRelated() {
		User user = new User("Mamun", "mamun@example.com", "secret");
		Address address = new Address("123 Main St", "Kiel", "12345", "Kieler");
		user.addAddress(address);
		userRepository.save(user);
	}
	
	@Transactional
	public void deleteRelated() {
		User user = userRepository.findById(14L).orElseThrow();
		Address address = user.getAddresses().getFirst();
		user.removeAddress(address);
		userRepository.save(user);
	}
}
