package br.com.samuel.fraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.samuel.fraud.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
