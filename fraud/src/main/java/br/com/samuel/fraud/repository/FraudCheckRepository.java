package br.com.samuel.fraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.samuel.fraud.domain.FraudCheckHistory;

public interface FraudCheckRepository extends JpaRepository<FraudCheckHistory, Integer> {


}
