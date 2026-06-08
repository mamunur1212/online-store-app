package com.example.store.services;

import com.example.store.entities.User;
import com.example.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final EntityManager entityManager;

	public UserService(UserRepository userRepository, EntityManager entityManager) {
		this.userRepository = userRepository;
		this.entityManager = entityManager;
	}

	@Transactional
	public void showEntityStates() {
		User user = new User("Mamun", "mamun@example.com", "secret");
		
		if (entityManager.contains(user))
			System.out.println("Persistent");
		else
			System.out.println("Transient / Detached");
		
		userRepository.save(user);
		
		if (entityManager.contains(user))
			System.out.println("Persistent");
		else
			System.out.println("Transient / Detached");
	}
}
