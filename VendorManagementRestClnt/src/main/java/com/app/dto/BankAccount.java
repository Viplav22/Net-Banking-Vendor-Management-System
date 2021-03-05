package com.app.dto;

public class BankAccount {
	private Integer id;
	private AcctType acctType;
	private double balance;
		
//	@JsonIgnoreProperties("accounts")
//	@JsonIgnore
	private Customer acctOwner;
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}
	public BankAccount(AcctType acctType, double balance) {
		super();
		this.acctType = acctType;
		this.balance = balance;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AcctType getAcctType() {
		return acctType;
	}
	public void setAcctType(AcctType acctType) {
		this.acctType = acctType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
//	@JsonIgnore //: ignore this property while serialization(marshalling)
	public Customer getAcctOwner() {
		return acctOwner;
	}
//	@JsonProperty //: DO NOT ignore this property while de serialization(un marshalling)
	public void setAcctOwner(Customer acctOwner) {
		this.acctOwner = acctOwner;
	}
	@Override
	public String toString() {
		return "BankAccount [acctType=" + acctType + ", balance=" + balance + ", getId()=" + getId() + "]";
	}
	
	
}
