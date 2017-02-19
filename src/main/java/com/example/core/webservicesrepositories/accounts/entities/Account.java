package com.example.core.webservicesrepositories.accounts.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String authorities;

	@NotNull
	@NotEmpty
	private String password;

	public Account(Long id, String name, String password, String username, String authorities) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}

	public Account (Long id) {
		super();
		this.id = id;
	}
	
	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", username=" + username + ", authorities=" + authorities
				+ ", password=" + password + "]";
	}

}
