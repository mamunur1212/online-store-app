package com.example.store.repositories;

import com.example.store.dtos.UserSummary;
import com.example.store.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

	// Find users whose profile is above a loyalty-point threshold, sorted by email.
	@Query("select u.id as id, u.email as email from Profile p join p.user u " +
			"where p.loyaltyPoints > :loyaltyPoints order by u.email")
	List<UserSummary> findByLoyaltyPointsGreaterThan(@Param("loyaltyPoints") Integer loyaltyPoints);
}


