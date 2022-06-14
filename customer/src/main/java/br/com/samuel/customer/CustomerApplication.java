package br.com.samuel.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(CustomerApplication.class, args);
//		displayAllBeans();
	}

	public static void displayAllBeans() {
//		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		System.out.println(String.valueOf(applicationContext.containsBean("producerConfig")) +
				String.valueOf(applicationContext.containsBean("producerFactory")) +
				String.valueOf(applicationContext.containsBean("kafkaTemplate")));
		System.out.println(applicationContext.getBean("producerConfig"));
		System.out.println("\n **************************************************** \n"
				+ applicationContext.getBean("producerFactory"));
		System.out.println("\n **************************************************** \n"
				+ applicationContext.getBean("kafkaTemplate"));
		
//		for (String beanName : allBeanNames) {
//			System.out.println(beanName);
//		}
	}

}
