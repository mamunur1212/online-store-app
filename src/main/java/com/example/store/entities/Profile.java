package com.example.store.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	@Column(name = "bio")
	private String bio;

	@Column(name = "phone_number", length = 15)
	private String phoneNumber;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "loyalty_points")
	private Integer loyaltyPoints;

	public Profile() {

	}

	public Profile(Long id, String bio, String phoneNumber, LocalDate dateOfBirth, Integer loyaltyPoints) {
		this.id = id;
		this.bio = bio;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.loyaltyPoints = loyaltyPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(Integer loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	@Override
	public String toString() {
		return "Profile{id=" + id + ", bio='" + bio + "', phoneNumber='" + phoneNumber +
				"', dateOfBirth=" + dateOfBirth + ", loyaltyPoints=" + loyaltyPoints + "}";
	}
}
