package com.dmitrii;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository repository) {
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		
		repository.save(customer);
	}
}
