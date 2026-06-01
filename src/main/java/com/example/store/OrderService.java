package com.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final PaymentService paymentService;
	
	@Autowired
	public OrderService(@Qualifier("payPalPaymentService") PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	public void placeOrder() {
		paymentService.processPayment(100.0);
	}
}
