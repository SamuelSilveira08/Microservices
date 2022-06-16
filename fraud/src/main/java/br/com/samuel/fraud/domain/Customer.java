package br.com.samuel.fraud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
}
