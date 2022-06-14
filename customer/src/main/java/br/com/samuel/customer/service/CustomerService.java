package br.com.samuel.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.samuel.customer.domain.Customer;
import br.com.samuel.customer.dto.CustomerDto;
import br.com.samuel.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	@SuppressWarnings(value = { "unused" })
	private final RestTemplate restTemplate;

	private KafkaTemplate<String, Customer> kafkaTemplate;

	public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
		super();
		this.customerRepository = customerRepository;
		this.restTemplate = restTemplate;
	}

	public void registerCustomer(CustomerDto registeringCustomer) {

		Customer customer = Customer.builder().firstName(registeringCustomer.firstName())
				.lastName(registeringCustomer.lastName()).email(registeringCustomer.email()).build();
		customerRepository.saveAndFlush(customer);
//		FraudCheckDto fraudCheck = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//				FraudCheckDto.class, customer.getId());
		kafkaTemplate.send("customer-topic", customer);

//		if (fraudCheck == null) {
//			throw new NullPointerException("Service fraud didn't return a fraud check");
//		}
//		if (fraudCheck.isFraudster()) {
//			throw new IllegalStateException("Customer is a fraudster");
//		}
	}

	@Autowired
	public void setKafkaTemplate(KafkaTemplate<String, Customer> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

}
