package com.enigma.PulsaProject.Dto;

import java.math.BigDecimal;
import java.util.Date;

public class ResponseTransaction {
	private Integer transactionId;
	private Date transactionDate;
	private String type;
	private String from;
	private String to;
	private BigDecimal amount;
	private String description;
	public ResponseTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseTransaction(Integer transactionId, Date transactionDate, String type, String from, String to,
			BigDecimal amount, String description) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.type = type;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.description = description;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
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
