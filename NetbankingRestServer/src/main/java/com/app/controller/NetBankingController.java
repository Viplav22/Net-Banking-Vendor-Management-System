package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BankAccounts;
import com.app.pojos.BankAccount;
import com.app.service.IBankAccountService;
import com.app.service.ICustomerService;

@RestController // Mandatory class level annotation
//@RestController => @Controller on the cls level + @ResponseBody on the ret types of 
//all req handling methods, annotated with @RequestMapping / @GetMapping.....
@RequestMapping("/accounts") // Resource
public class NetBankingController {
	// 2 dependency injecting

	// customer service i/f
	@Autowired
	private ICustomerService customerService;

	// bank account service i/f
	@Autowired
	private IBankAccountService acctService;

	public NetBankingController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// add REST clnt request handling method : for supplying all accounts for the
	// authenticated bank customer
	@GetMapping("/customers/{custId}/{pwd}")
	// custID, pwd : path variables
	// in your real project will you ever send password over the open wire like this
	// we have to apply a Spring security(https) : 1st spring security, basic
	// authentication, JWT based authentication
	// then there is a configuration class to change from http to https
	public ResponseEntity<?> listAccountsForCustomer(@PathVariable long custId, @PathVariable String pwd) {
		System.out.println("in list accts " + custId + " " + pwd);
		// invoking service method for getting bank accts
		List<BankAccount> accounts = customerService.getAllAccountsForCustomer(custId, pwd);
		// in case customer has given the wrong credentials we will get an exception in
		// above line with a exception stack trace and if not handled it will be sent
		// all the way to front end 
		
		//to handle this we can wrap this line with try catch block 
		// and in case of error in catch block  we should create a new response entity 
		// which contains error data : with reason of failure and whatever details you want to put
		// and send that msg
		// one try catch block per method per controller : boilerplate code
		
		// Better approach is centralized Exception handling// @ControllerAdvice
		// to handle all the exceptions(custom) we have defined a GlobalExceptionHandler

		// => credentials are correct but he doesn't have any accounts
		// if accts list is empty => SC 204 (NO CONTENT) , non empty => SC 200 n list of
		// accts
		if (accounts.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// non empty list
		return new ResponseEntity<>(new BankAccounts(accounts), HttpStatus.OK);
	}

	// add REST clnt request handling method : for getting acct summary
	@GetMapping("/{acctId}")
	public ResponseEntity<?> getAccountSummary(@PathVariable int acctId) {
		System.out.println("in get a/c summary " + acctId);
		return ResponseEntity.ok(acctService.getAccountSummary(acctId));
	}

	@PostMapping // for creating a new resource
	// @RequestBody => un marshalling(=de serial) json to java
	public ResponseEntity<?> createAccount(@RequestBody BankAccount a) {
		// expected i/p data : BankAccount : type n balance n acctOwner : Customer : customer id
		System.out.println("in create a/c " + a + " " + a.getAcctOwner());
		return ResponseEntity.ok(acctService.createAccount(a));
	}

	@PutMapping("/{acctId}") // for updating account info.(eg : balance)
	public ResponseEntity<?> updateAccount(@PathVariable int acctId, @RequestBody BankAccount a) {
		System.out.println("in update a/c " + a + " " + acctId);

		return ResponseEntity.ok(acctService.updateAccount(acctId, a));
	}

	@DeleteMapping("/{acctId}")
	public ResponseEntity<?> deleteAccountSummary(@PathVariable int acctId) {
		System.out.println("in delete a/c summary " + acctId);
		return ResponseEntity.ok(acctService.deleteAccountSummary(acctId));
	}

}
