package com.dmitrii.kafka;

import com.dmitrii.clients.fraud.NotificationRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
	
	public Map<String, Object> consumerConfig() {
		HashMap<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, IntegerDeserializer.class);
//		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, NotificationRequest> consumerFactory() {
		JsonDeserializer<NotificationRequest> jsonDeserializer = new JsonDeserializer<>();
		jsonDeserializer.addTrustedPackages("com.dmitrii.clients.fraud");
		return new DefaultKafkaConsumerFactory<>(
				consumerConfig(),
				new StringDeserializer(),
				jsonDeserializer
		);
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NotificationRequest>> kafkaListenerContainerFactory () {
		ConcurrentKafkaListenerContainerFactory<String, NotificationRequest> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
