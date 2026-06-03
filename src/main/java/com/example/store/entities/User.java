package com.example.store.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(nullable = false, name = "email", unique = true)
	private String email;
	
	@Column(nullable = false, name = "password")
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "user_tags",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private Set<Tag> tags = new HashSet<>();

	public User() {
	
	}

	public Long getId() {
			return id;
		}
	public User(Long id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.add(address);
		address.setUser(this);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setUser(null);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getUsers().add(this);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getUsers().remove(this);
	}

	@Override
	public String toString() {
		return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
	}
}
