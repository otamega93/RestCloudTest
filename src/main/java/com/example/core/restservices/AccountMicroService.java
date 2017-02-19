package com.example.core.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.core.RestCloudTestApplication;
import com.example.core.restservices.accounts.services.AccountRestService;
import com.example.core.restservices.restcontrollers.AccountRestController;


@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class AccountMicroService {

	public static void main(String[] args) {
		// Tell server to look for registration.properties or registration.yml
		System.setProperty("spring.config.name", "AccountMicroServiceClient");
		SpringApplication.run(AccountMicroService.class, args);
	}
	
	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNT-WEB-SERVICE-REPOSITORY";
	
	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton" 
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public AccountRestService accountRestService() {
		return new AccountRestService(ACCOUNTS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public AccountRestController accountRestController() {
		return new AccountRestController(accountRestService());
	}
	
}
