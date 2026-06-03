package com.example.store.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, name = "street")
	private String street;

	@Column(nullable = false, name = "city")
	private String city;

	@Column(nullable = false, name = "zipcode")
	private String zipcode;

	@Column(nullable = false, name = "state")
	private String state;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Address() {

	}

	public Address(Long id, String street, String city, String zipcode, String state, User user) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address{id=" + id + ", street='" + street + "', city='" + city +
				"', zipcode='" + zipcode + "', state='" + state + "'}";
	}
}
