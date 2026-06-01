package com.example.store;

public class OrderService {
	public void placeOrder() {
		StripePaymentService paymentService = new StripePaymentService();
		paymentService.processPayment(100.0);
	}
}
