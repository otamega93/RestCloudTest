package com.example.core.restservices.accounts.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.core.custom.CustomPageImpl;
import com.example.core.webservicesrepositories.accounts.entities.Account;


@Service
@Transactional
public class AccountRestService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	protected List<Account> accounts = new ArrayList<>();

	protected Logger logger = Logger.getLogger(AccountRestService.class
			.getName());

	public AccountRestService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	@Transactional(readOnly=true)
	public PagedResources<AccountResource> findAll(Pageable pageable) throws RestClientException, URISyntaxException {
		System.out.println("url: " + serviceUrl + "/accounts");

		return restTemplate.exchange(serviceUrl + "/accounts"
				+ "?page={page}&size={size}&sort={sort}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<AccountResource>>(){}, pageable.getPageNumber(),
				pageable.getPageSize(), pageable.getSort()).getBody();
	}

	@Transactional(readOnly=false)
	public AccountResource create(Account account) {
		//Doesnt work :S
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", account.getName());
		map.add("username", account.getUsername());
		map.add("password", account.getPassword());
		map.add("authorities", account.getAuthorities());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
		        headers);
		
		return restTemplate.exchange(serviceUrl + "/accounts", HttpMethod.POST, entity, AccountResource.class).getBody();
	}
}
