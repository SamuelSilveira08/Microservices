package br.com.samuel.customer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.customer.dto.CustomerDto;
import br.com.samuel.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

	@PostMapping
	public void registerCostumer(@RequestBody CustomerDto customer) {
		customerService.registerCustomer(customer);
		log.info("new customer registration {}", customer);
	}	

}
