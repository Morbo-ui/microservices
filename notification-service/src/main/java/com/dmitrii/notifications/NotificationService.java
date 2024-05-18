package com.dmitrii.notifications;

import com.dmitrii.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {
	
	private final NotificationRepository notificationRepository;
	
	public void send(NotificationRequest notificationRequest) {
		notificationRepository.save(
				Notification.builder()
						.toCustomerId(notificationRequest.toCustomerId())
						.toCustomerEmail(notificationRequest.toCustomerName())
						.sender("Dmitrii")
						.message(notificationRequest.message())
						.sentAt(LocalDateTime.now())
						.build()
		);
	}
	
}
