package br.com.samuel.fraud.deserializer;

import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.samuel.fraud.domain.Customer;

public class CustomerDeserializer implements Deserializer<Customer> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Customer deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				System.out.println("Null received at deserializing");
				return null;
			}
			System.out.println("Deserializing...");
			return objectMapper.readValue(new String(data, "UTF-8"), Customer.class);
		} catch (Exception e) {
			throw new SerializationException("An error occured while trying to deserialize byte[] to Customer");
		}
	}

}
