package br.com.samuel.fraud.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.samuel.fraud.domain.FraudCheckHistory;
import br.com.samuel.fraud.repository.FraudCheckRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FraudCheckService {

	private final FraudCheckRepository repository;

	public boolean isFraudulentCustomer(int customerId) {
		repository.save(FraudCheckHistory.builder()
				.isFraudster(false)
				.customerId(customerId)
				.createdAt(LocalDateTime.now())
				.build());
		return false;
	}

}
