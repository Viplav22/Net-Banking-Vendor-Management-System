package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.AccountHandlingException;
import com.app.dao.BankAccountRepository;
import com.app.pojos.BankAccount;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {
	@Autowired
	private BankAccountRepository acctRepo;

	@Override
	public BankAccount getAccountSummary(int acctId) {
		// invoke DAO layer's method n handle Optional
		return acctRepo.findById(acctId).orElseThrow(() -> new AccountHandlingException("Invalid Account ID !!!!"));
	}

	@Override
	public BankAccount createAccount(BankAccount a) {
		// System.out.println("in service "+a);
		// System.out.println(a.getAcctOwner());
		return acctRepo.save(a);
	}
	/*
	{
	    "acctType": "FD",
	    "balance": 10000,
	    "acctOwner": {
	        "customerId": 123452
	    }
	}
*/
	@Override
	public BankAccount updateAccount(int acctId, BankAccount a) {

		System.out.println("in service " + a);
		System.out.println(a.getAcctOwner());
		BankAccount account = acctRepo.findById(acctId)
				.orElseThrow(() -> new AccountHandlingException("A/C Updates failed !!!: invalid a/c id"));
		// => valid acct id , validated ONLY path var acct id
		// account : persistent
		account.setBalance(a.getBalance());//changing the state of PERSISTENT entity
		return account;
		//updating just balance for full proof that correct data gets updated
		
// update query will be fired @ tx.commit : when transactional anno method rets
								// (i.e when service method rets)
		// return acctRepo.save(a);
		// not using save because it was inserting new rec when we were giving non existing id(bank) as i/p
	}
	// we have to give the id too
	/*
	 {
    "id" : 2,
    "acctType": "FD",
    "balance": 10000,
        "acctOwner": {
        "customerId": 123450
    }
} 
	 */

	@Override
	public BankAccount deleteAccountSummary(int acctId) {
		BankAccount a = acctRepo.findById(acctId)
				.orElseThrow(() -> new AccountHandlingException("A/C Closing failed : Invalid Account ID !!!!"));
		acctRepo.deleteById(acctId);
		return a;
	}

}
