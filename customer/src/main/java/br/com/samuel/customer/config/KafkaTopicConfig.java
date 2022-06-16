package br.com.samuel.customer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic customerTopic() {
		return TopicBuilder.name("customer-topic")
				.config(TopicConfig.RETENTION_MS_CONFIG, "60000")
				.build();
	}

}
