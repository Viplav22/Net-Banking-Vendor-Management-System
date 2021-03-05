package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Customer {

	private Long customerId;

	@NotBlank(message = "Name is required")
	@Length(min = 3, max = 15, message = "Invalid len of Name")
	private String name = "abc";

	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	private String password;

//	@JsonIgnoreProperties("acctOwner")
	private List<BankAccount> accounts = new ArrayList<>();

	public Customer() {
		System.out.println("in vendor ctor");
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	// helper methods to add n remove acct
	public void addAccount(BankAccount a) {
		accounts.add(a);
		a.setAcctOwner(this);
	}

	public void removeAccount(BankAccount a) {
		accounts.remove(a);
		a.setAcctOwner(null);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", password=" + password + "]";
	}

}
