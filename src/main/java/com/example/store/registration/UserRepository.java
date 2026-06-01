package com.example.store.registration;

public interface UserRepository {
	void save(User user);
	User findByEmail(String email);
}
