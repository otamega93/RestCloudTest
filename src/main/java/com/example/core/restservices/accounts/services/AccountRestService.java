package com.example.core.restservices.accounts.services;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.core.webservicesrepositories.accounts.entities.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Http.Header;
import com.sun.net.httpserver.Headers;

@Service
@Transactional
public class AccountRestService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected List<Account> accounts = new ArrayList<>();

	protected Logger logger = Logger.getLogger(AccountRestService.class.getName());

	public AccountRestService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	@Transactional(readOnly = true)
	public AccountResource findOne(Long id) {

		System.out.println("Usa RestTemplate");
		return restTemplate.getForEntity(serviceUrl + "/accounts/{id}", AccountResource.class, id).getBody();
	}

	@Transactional(readOnly = true)
	@HystrixCommand(fallbackMethod="test")
	public ResponseEntity<PagedResources<AccountResource>> findAll(Pageable pageable) throws RestClientException, URISyntaxException {
		System.out.println("url: " + serviceUrl + "/accounts");

		System.out.println("Usa RestTemplate");

		return restTemplate.exchange(serviceUrl + "/accounts" + "?page={page}&size={size}&sort={sort}", HttpMethod.GET,
				null, new ParameterizedTypeReference<PagedResources<AccountResource>>() {
				}, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
	}

	@Transactional(readOnly = false)
	public AccountResource create(Account account) {

		System.out.println("Usa RestTemplate");
		return restTemplate.postForEntity(serviceUrl + "/accounts", account, AccountResource.class).getBody();

	}
	
	public ResponseEntity<PagedResources<AccountResource>> test (Pageable pageable) {
		return new ResponseEntity<PagedResources<AccountResource>>(HttpStatus.I_AM_A_TEAPOT);
	}

}
