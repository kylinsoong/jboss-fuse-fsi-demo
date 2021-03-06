package com.redhat.poc.rest;

public class Transaction {

	private Integer fundNumber;
	private Integer balance;
	private Boolean denied;
	private String deniedCause;
	private String transactionType;
	private String fundName;
    private String fundType;
    private String fundStatus;
	
	public Transaction(Integer fundNumber, Integer balance, Boolean denied, String deniedCause, String transactionType,
			String fundName, String fundType, String fundStatus) {
		super();
		this.fundNumber = fundNumber;
		this.balance = balance;
		this.denied = denied;
		this.deniedCause = deniedCause;
		this.transactionType = transactionType;
		this.fundName = fundName;
		this.fundType = fundType;
		this.fundStatus = fundStatus;
	}

	public Integer getFundNumber() {
		return fundNumber;
	}

	public void setFundNumber(Integer fundNumber) {
		this.fundNumber = fundNumber;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Boolean getDenied() {
		return denied;
	}

	public void setDenied(Boolean denied) {
		this.denied = denied;
	}

	public String getDeniedCause() {
		return deniedCause;
	}

	public void setDeniedCause(String deniedCause) {
		this.deniedCause = deniedCause;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
}
