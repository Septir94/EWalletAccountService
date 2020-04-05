package com.enigma.PulsaProject.Dto;

import java.math.BigDecimal;

public class AccountPutDto {
	private String account;
	private BigDecimal balance;
	
	public AccountPutDto() {
		// TODO Auto-generated constructor stub
	}
	public AccountPutDto(String account, BigDecimal balance) {
		super();
		this.account = account;
		this.balance = balance;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
