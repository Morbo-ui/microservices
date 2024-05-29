package com.dmitrii.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
		scanBasePackages = {
				"com.dmitrii.notifications",
				"com.dmitrii.amqp",
		}
)
@EnableFeignClients
@EnableEurekaClient
@PropertySources({
		@PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationsApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}
}
