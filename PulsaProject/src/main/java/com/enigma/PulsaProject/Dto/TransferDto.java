package com.enigma.PulsaProject.Dto;

import java.math.BigDecimal;

public class TransferDto {
	private String account;
	private String to;
	private BigDecimal amount;
	private String description;
	
	public TransferDto() {
		// TODO Auto-generated constructor stub
	}
	public TransferDto(String account, String to, BigDecimal amount, String description) {
		super();
		this.account = account;
		this.to = to;
		this.amount = amount;
		this.description = description;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
