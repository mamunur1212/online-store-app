package com.example.store.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
	private final NotificationService notificationService;

	@Autowired
	public NotificationManager(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void notify(String message) {
		notificationService.send(message);
	}
}
