package com.app.service;

import java.util.List;

import com.app.dto.BankAccount;

public interface INetbankingService {
	List<BankAccount> authenticateCustomer(long custId, String pwd);
	
	// add a method to make POST call to backend for a/c creation purpose
	BankAccount createAccount(BankAccount acct);
}
