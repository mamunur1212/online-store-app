package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

public static void main(String[] args) {
	// SpringApplication.run(StoreApplication.class, args);
	OrderService orderService = new OrderService();
	orderService.setPaymentService(new StripePaymentService());
	orderService.placeOrder();
}

}
