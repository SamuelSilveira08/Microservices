package br.com.samuel.customer.serializer;

import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.samuel.customer.domain.Customer;

public class CustomerSerializer implements Serializer<Customer> {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, Customer data) {
		try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
		} catch(Exception e) {
			throw new SerializationException("An error occured while serializing object");
		}
	}
	
	

}
