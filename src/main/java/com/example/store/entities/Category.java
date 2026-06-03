package com.example.store.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Byte id;

	@Column(nullable = false, name = "name")
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products = new ArrayList<>();

	public Category() {

	}

	public Category(Byte id, String name) {
		this.id = id;
		this.name = name;
	}

	public Byte getId() {
		return id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		products.add(product);
		product.setCategory(this);
	}

	public void removeProduct(Product product) {
		products.remove(product);
		product.setCategory(null);
	}

	@Override
	public String toString() {
		return "Category{id=" + id + ", name='" + name + "'}";
	}
}
