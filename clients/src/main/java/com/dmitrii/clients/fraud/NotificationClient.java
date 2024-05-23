package com.dmitrii.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
		name = "notification-service",
		url = "${clients.notification.url}"
)
public interface NotificationClient {
	
	@PostMapping(path = "api/v1/notification")
	void send(@RequestBody NotificationRequest request);
}
