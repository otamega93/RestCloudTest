package com.example.core.webservicesrepositories.accounts.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.core.webservicesrepositories.accounts.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

}
