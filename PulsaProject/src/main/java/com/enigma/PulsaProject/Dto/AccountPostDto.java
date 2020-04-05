package com.enigma.PulsaProject.Dto;

import java.math.BigDecimal;

public class AccountPostDto {

	private String account;
	private String name;
	private BigDecimal balance;

	public AccountPostDto() {
		// TODO Auto-generated constructor stub
	}

	public AccountPostDto(String account, String name, BigDecimal balance) {
		super();
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
	
	
}
