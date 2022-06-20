package br.com.samuel.fraud.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {

	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
}
