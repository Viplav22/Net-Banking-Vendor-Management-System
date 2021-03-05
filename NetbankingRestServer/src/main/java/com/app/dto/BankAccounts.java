package com.app.dto;
//dto : data transfer obj
// these dtos are going to be exchanged b/w rest clnt and rest server to communicate data
import java.util.List;

import com.app.pojos.BankAccount;

// wrapper class for wrapping all the accounts list nothing else
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
