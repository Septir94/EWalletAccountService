package com.enigma.PulsaProject.Dto;

import java.math.BigDecimal;


public class AccountDto {
	private Integer id;
	private String account;
	private String name;
	private BigDecimal balance;
	private Double point;
	private Short status;
	public AccountDto() {
		// TODO Auto-generated constructor stub
	}
	public AccountDto(Integer id, String account, String name, BigDecimal balance, Double point, Short status) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.balance = balance;
		this.point = point;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

	
	
}
