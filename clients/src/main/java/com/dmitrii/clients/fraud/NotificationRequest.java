package com.dmitrii.clients.fraud;

public record NotificationRequest(
		Integer toCustomerId,
		String toCustomerName,
		String message
) {
}
