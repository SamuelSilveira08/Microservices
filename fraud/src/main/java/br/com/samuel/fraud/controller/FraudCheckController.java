package br.com.samuel.fraud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.fraud.domain.Customer;
import br.com.samuel.fraud.dto.FraudCheckDto;
import br.com.samuel.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudCheckController {
	
	private final FraudCheckService service;

	@PostMapping
	public FraudCheckDto isFraudster(@RequestBody Customer customer) {
		boolean isFraudulentCustomer = service.isFraudulentCustomer(customer);
		log.info("Successfully executed fraud check for customer {}", customer);
		return new FraudCheckDto(isFraudulentCustomer);
	}

}
