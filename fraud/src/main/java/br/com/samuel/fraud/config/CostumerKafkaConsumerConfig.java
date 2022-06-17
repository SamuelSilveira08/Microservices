package br.com.samuel.fraud.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.samuel.fraud.domain.Customer;

@Configuration
@EnableKafka
public class CostumerKafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean("consumerConfig")
	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		return props;
	}

	@Bean
	public ConsumerFactory<String, Customer> consumerFactory() {
		JsonDeserializer<Customer> deserializer = new JsonDeserializer<>(Customer.class);
		deserializer.trustedPackages("*");
		deserializer.ignoreTypeHeaders();
		return new DefaultKafkaConsumerFactory<String, Customer>(consumerConfig(), new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory(
			ConsumerFactory<String, Customer> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, Customer> listenerFactory = new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		listenerFactory.setConsumerFactory(consumerFactory);
		return listenerFactory;
	}

}
