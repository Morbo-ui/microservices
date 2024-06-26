package com.dmitrii.customer;

import com.dmitrii.amqp.RabbitMQMessageProducer;
import com.dmitrii.clients.fraud.FraudCheckResponse;
import com.dmitrii.clients.fraud.FraudClient;
import com.dmitrii.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerRepository repository;
	private final FraudClient fraudClient;
	private final RabbitMQMessageProducer producer;
	
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
		
		NotificationRequest notificationRequest = new NotificationRequest(
				customer.getId(),
				customer.getEmail(),
				String.format("Hi %s, welcome to our service...",
						customer.getFirstName())
		);
		
		producer.publish(
				notificationRequest,
				"internal.exchange",
				"internal.notification.routing-key"
				);
	}
}
