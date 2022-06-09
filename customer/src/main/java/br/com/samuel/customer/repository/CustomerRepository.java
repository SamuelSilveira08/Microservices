package br.com.samuel.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.samuel.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
