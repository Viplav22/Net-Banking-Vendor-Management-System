package com.app.service;

import java.util.List;

import com.app.pojos.BankAccount;
import com.app.pojos.Customer;

public interface ICustomerService {
   List<BankAccount>getAllAccountsForCustomer(long custId,String pwd);
   Customer createAccount(Customer customer);
}
