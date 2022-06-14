package br.com.samuel.customer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import br.com.samuel.customer.domain.Customer;
import br.com.samuel.customer.serializer.CustomerSerializer;

// Lazy annotation on class level so all methods are loaded lazily to avoid circular dependencies problem.

@Configuration
public class KafkaProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean("producerConfig")
	public Map<String, Object> producerConfig() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomerSerializer.class);
		return properties;
	}

	// Using dependency injection to set producerConfig into the producerFactory
	@Bean("producerFactory")
	public ProducerFactory<String, Customer> producerFactory(Map<String, Object> producerConfig) {
		return new DefaultKafkaProducerFactory<String, Customer>(producerConfig);
	}

	@Bean("kafkaTemplate")
	public KafkaTemplate<String, Customer> kafkaTemplate(ProducerFactory<String, Customer> producerFactory) {
		return new KafkaTemplate<String, Customer>(producerFactory);
	}

}
