package com.example.store;

import com.example.store.notification.NotificationManager;
import com.example.store.registration.User;
import com.example.store.registration.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(StoreApplication.class, args);
	//	UserService userService = context.getBean(UserService.class);
	//	User user1 = new User(1L, "John", "hello1@gmail.com", "123456");
	//	userService.registerUser(user1);
	//	User user2 = new User(2L, "Jane", "hello2@gmail.com", "123456");
	//	userService.registerUser(user2);
	//	User user3 = new User(3L, "Alice", "hello3@gmail.com", "sssss");
	//	userService.registerUser(user3);
	//	OrderService orderService = context.getBean(OrderService.class);
	//	orderService.placeOrder();
	//	NotificationManager notificationManager = context.getBean(NotificationManager.class);
	//	notificationManager.notify("Your order has been placed successfully!");
	}

}
