package br.com.samuel.customer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import br.com.samuel.customer.domain.Customer;
import br.com.samuel.customer.serializer.CustomerSerializer;


@Configuration
public class KafkaProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomerSerializer.class);
		return properties;
	}

	@Bean
	public ProducerFactory<String, Customer> producerFactory() {
		return new DefaultKafkaProducerFactory<String, Customer>(producerConfig());
	}

	@Bean
	public KafkaTemplate<String, Customer> kafkaTemplate(ProducerFactory<String, Customer> producerFactory) {
		return new KafkaTemplate<String, Customer>(producerFactory);
	}

}
