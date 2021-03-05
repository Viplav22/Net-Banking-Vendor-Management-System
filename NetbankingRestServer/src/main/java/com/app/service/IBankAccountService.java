package com.app.service;

import com.app.pojos.BankAccount;
import com.app.pojos.Customer;

public interface IBankAccountService {
	BankAccount getAccountSummary(int acctId);
	BankAccount createAccount(BankAccount a);
	BankAccount updateAccount(int acctId,BankAccount a);
	BankAccount deleteAccountSummary(int acctId);
	
}
