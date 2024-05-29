package com.dmitrii.notifications.kafka;

import com.dmitrii.clients.fraud.NotificationRequest;
import com.dmitrii.notifications.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class NotificationKafkaConsumer {
	
	private final NotificationService notificationService;
	
	
	@KafkaListener(
			topics = "microservices",
			groupId = "com.dmitrii"
	)
	public void consumer(NotificationRequest notificationRequest) {
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.send(notificationRequest);
	}
}
