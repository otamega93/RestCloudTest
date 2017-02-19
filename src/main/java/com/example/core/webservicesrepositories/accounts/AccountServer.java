package com.example.core.webservicesrepositories.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.example.core.RestCloudTestApplication;

@SpringBootApplication
@EnableEurekaClient
public class AccountServer {

	public static void main(String[] args) {
		// Tell server to look for registration.properties or registration.yml
		System.setProperty("spring.config.name", "AccountServerClient");
		SpringApplication.run(AccountServer.class, args);
	}
}
