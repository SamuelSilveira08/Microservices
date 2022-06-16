package br.com.samuel.fraud.service;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.samuel.fraud.domain.Customer;
import br.com.samuel.fraud.domain.FraudCheckHistory;
import br.com.samuel.fraud.repository.CustomerRepository;
import br.com.samuel.fraud.repository.FraudCheckRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FraudCheckService {

	private final FraudCheckRepository fraudRepository;
	private final CustomerRepository customerRepository;

	@KafkaListener(topics = "customer-topic", groupId = "customer.topic.group", containerFactory = "kafkaListenerContainerFactory")
	public boolean isFraudulentCustomer(@Payload Customer customer) {
		log.info(customer.toString());
		
		customerRepository.save(customer);
		
		fraudRepository.save(FraudCheckHistory.builder()
				.isFraudster(false)
				.customer(customer)
				.createdAt(LocalDateTime.now())
				.build());
		
		return false;
	}

}
