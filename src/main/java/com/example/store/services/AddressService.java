package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.repositories.AddressRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public void fetchAddress() {
		Address address = addressRepository.findById(11L).orElseThrow();
		System.out.println(address);
	}
}
