package com.dmitrii.notifications.rabbitmq;

import com.dmitrii.clients.fraud.NotificationRequest;
import com.dmitrii.notifications.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class NotificationConsumer {
	
	private final NotificationService notificationService;
	
	@RabbitListener(queues = "${rabbitmq.queue.notification}")
	public void consumer(NotificationRequest notificationRequest) {
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.send(notificationRequest);
	}
}
