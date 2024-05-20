package com.dmitrii.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
		scanBasePackages = {
				"com.dmitrii.notifications",
				"com.dmitrii.amqp",
		}
)
@EnableFeignClients
@EnableEurekaClient
public class NotificationsApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}
}
