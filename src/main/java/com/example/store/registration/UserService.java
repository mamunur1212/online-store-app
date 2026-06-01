package com.example.store.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final NotificationService notificationService;

	@Autowired
	public UserService(UserRepository userRepository, NotificationService notificationService) {
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}

	public void registerUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists.");
		}
		userRepository.save(user);
		notificationService.send("Welcome " + user.getName() + "!", user.getEmail());
	}
}
