package com.dmitrii.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class KafkaMessageProducer {
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	
	public void publish(String topic, String key, @Payload Object payload) {
		log.info("Publishing to {} with key{}. Payload {}", topic, key, payload);
		kafkaTemplate.send(topic, key, payload);
		log.info("Published to {} with key{}. Payload {}", topic, key, payload);
		
	}
}
