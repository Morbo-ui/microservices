package com.dmitrii.notifications;

import com.dmitrii.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
	
	private final NotificationService service;
	
	@PostMapping
	public void send(@RequestBody NotificationRequest request) {
		log.info("New notification... {}" , request);
		service.send(request);
	}
}
