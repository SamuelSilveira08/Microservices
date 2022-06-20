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

		// customer comes without id
		// maybe I have to add it before setting customer into fraudCheckHistory
		
		// But why is customers being saved with a gap between the ids?
		// Like 1, 3, 5
		// It's because of the exception that is being thrown, but why exactly it skips one number?
		
		// Well, actually it's related to hibernate sequence. I don't know why, but it's not defining
		// one id sequence for each table, but for the two tables in database
		
		customerRepository.save(customer);
		
		System.out.println(customer.getId());
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fraudRepository.save(FraudCheckHistory.builder().isFraudster(false).customer(customer)
				.createdAt(LocalDateTime.now()).build());

		return false;
	}

}
