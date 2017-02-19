package com.example.core.restservices.restcontrollers;

import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.example.core.restservices.accounts.services.AccountResource;
import com.example.core.restservices.accounts.services.AccountResourceAssembler;
import com.example.core.restservices.accounts.services.AccountRestService;
import com.example.core.webservicesrepositories.accounts.entities.Account;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
	
	@Autowired
	private AccountResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Account> pageAssembler;
	
	@Autowired
	protected AccountRestService accountRestService;

	protected Logger logger = Logger.getLogger(AccountRestController.class
			.getName());
	
	public AccountRestController(AccountRestService accountRestService) {
		this.accountRestService = accountRestService;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public PagedResources<AccountResource> findAll(Pageable pageable) throws RestClientException, URISyntaxException {
		
		System.out.println(accountRestService.findAll(pageable).toString());
		PagedResources<AccountResource> accounts = accountRestService.findAll(pageable);
		return accounts;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST) 
	public ResponseEntity<AccountResource> create (@RequestBody Account account) {
		
		return new ResponseEntity<AccountResource>(accountRestService.create(account), HttpStatus.OK);
		
	}

}
