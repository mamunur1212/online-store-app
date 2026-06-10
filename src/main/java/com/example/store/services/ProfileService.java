package com.example.store.services;

import com.example.store.dtos.UserSummary;
import com.example.store.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
	private final ProfileRepository profileRepository;

	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Transactional
	public void fetchProfilesByLoyaltyPoints(Integer loyaltyPoints) {
		List<UserSummary> users = profileRepository.findByLoyaltyPointsGreaterThan(loyaltyPoints);
		System.out.println("Users with loyalty points > " + loyaltyPoints + ":");
		for (UserSummary user : users) {
			System.out.println("id: " + user.getId() + " | email: " + user.getEmail());
		}
	}
}
