package com.example.core.restservices.accounts.services;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.core.restservices.restcontrollers.AccountRestController;
import com.example.core.webservicesrepositories.accounts.entities.Account;


@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource>{

	public AccountResourceAssembler () {
		super(AccountRestController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource resource = new AccountResource();
		resource.setUsername(account.getUsername());
		resource.setName(account.getName());
		resource.setPassword(account.getPassword());
		resource.setAuthorities(account.getAuthorities());
		resource.setIds(account.getId());
	    resource.add(linkTo(AccountRestController.class).slash("").slash(account.getId()).withSelfRel());

	    return resource;
	}
}