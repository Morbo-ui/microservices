package com.dmitrii;

public record CustomerRegistrationRequest(
		String firstName,
		String lastName,
		String email
		) {
}
