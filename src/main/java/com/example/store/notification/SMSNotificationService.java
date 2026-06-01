package com.example.store.notification;

import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService implements NotificationService {
	@Override
	public void send(String message) {
		System.out.println("Sending SMS notification: " + message);
	}
}
