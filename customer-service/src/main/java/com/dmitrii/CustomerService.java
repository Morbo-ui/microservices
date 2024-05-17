package com.dmitrii;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerRepository repository;
	private final RestTemplate restTemplate;
	
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		repository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
				"http://FRAUD-SERVICE/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class,
				customer.getId()
		);
		
		if (Objects.requireNonNull(fraudCheckResponse).isFraudster()) {
			throw new IllegalStateException("Fraudster");
		}
	}
}
