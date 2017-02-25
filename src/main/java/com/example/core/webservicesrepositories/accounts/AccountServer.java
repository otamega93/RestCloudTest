package com.example.core.webservicesrepositories.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.example.core.RestCloudTestApplication;
import com.example.core.webservicesrepositories.accounts.entities.Account;

@SpringBootApplication
@EnableEurekaClient
public class AccountServer {

	public static void main(String[] args) {
		// Tell server to look for registration.properties or registration.yml
		System.setProperty("spring.config.name", "AccountServerClient");
		SpringApplication.run(AccountServer.class, args);
	}

//	@Bean
//	public ResourceProcessor<Resource<Account>> personProcessor() {
//
//		return new ResourceProcessor<Resource<Account>>() {
//
//			@Override
//			public Resource<Account> process(Resource<Account> resource) {
//
//				if(null != resource.getLink("self"))
//					resource.add(new Link("http://ACCOUNT-WEB-SERVICE-REPOSITORY/accounts", "self"));
//				
//				if(null != resource.getLink("account"))
//					resource.add(new Link("http://ACCOUNT-WEB-SERVICE-REPOSITORY/accounts", "accounts"));
//				
//				return resource;
//			}
//		};
//	}
}
