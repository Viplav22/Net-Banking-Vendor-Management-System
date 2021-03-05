package com.app.dto;

import java.util.List;

public class BankAccounts {
	private List<BankAccount> accounts;

	public BankAccounts() {
		// TODO Auto-generated constructor stub
	}

	public BankAccounts(List<BankAccount> accounts) {
		super();
		this.accounts = accounts;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

}
