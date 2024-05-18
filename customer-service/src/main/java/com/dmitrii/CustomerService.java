package com.dmitrii;

import com.dmitrii.clients.fraud.FraudCheckResponse;
import com.dmitrii.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerRepository repository;
	private final FraudClient fraudClient;
	
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		repository.saveAndFlush(customer);
		
		FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
		
		if (Objects.requireNonNull(fraudCheckResponse).isFraudster()) {
			throw new IllegalStateException("Fraudster");
		}
	}
}
