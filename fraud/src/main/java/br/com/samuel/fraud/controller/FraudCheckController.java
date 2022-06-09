package br.com.samuel.fraud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/{customerId}")
	public FraudCheckDto isFraudster(@PathVariable int customerId) {
		boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
		log.info("Successfully executed fraud check for customer {}", customerId);
		return new FraudCheckDto(isFraudulentCustomer);
	}

}
