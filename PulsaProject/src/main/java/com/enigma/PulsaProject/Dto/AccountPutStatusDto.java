package com.enigma.PulsaProject.Dto;

public class AccountPutStatusDto {
	private String account;
	private Short status;
	public AccountPutStatusDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountPutStatusDto(String account, Short status) {
		super();
		this.account = account;
		this.status = status;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
}
