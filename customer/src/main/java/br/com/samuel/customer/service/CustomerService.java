package br.com.samuel.customer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.samuel.customer.domain.Customer;
import br.com.samuel.customer.dto.CustomerDto;
import br.com.samuel.customer.dto.FraudCheckDto;
import br.com.samuel.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;
//	private final KafkaTemplate<String, Customer> kafkaTemplate;

	public void registerCustomer(CustomerDto registeringCustomer) {

		Customer customer = Customer.builder().firstName(registeringCustomer.firstName())
				.lastName(registeringCustomer.lastName()).email(registeringCustomer.email()).build();
		customerRepository.saveAndFlush(customer);
//		FraudCheckDto fraudCheck = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//				FraudCheckDto.class, customer.getId());
//		kafkaTemplate.send("customer-topic", customer);

//		if (fraudCheck == null) {
//			throw new NullPointerException("Service fraud didn't return a fraud check");
//		}
//		if (fraudCheck.isFraudster()) {
//			throw new IllegalStateException("Customer is a fraudster");
//		}
	}

}
