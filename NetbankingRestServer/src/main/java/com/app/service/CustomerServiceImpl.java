package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.CustomerHandlingException;
import com.app.dao.CustomerRepository;
import com.app.pojos.BankAccount;
import com.app.pojos.Customer;

@Service
@Transactional // OPtional BUT reco.
public class CustomerServiceImpl implements ICustomerService {
	// dependency : DAO layer i/f
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<BankAccount> getAllAccountsForCustomer(long custId, String pwd) {
		// invoke DAO's method
		Optional<Customer> optionalCust = customerRepo.fetchDetailsCustomerIdAndPassword(custId, pwd);
//		if(optionalCust.isPresent())
//			optionalCust.get().getAccounts().size();//access the size of the collection : in the PERSISTENT state
		return optionalCust.orElseThrow(() -> new CustomerHandlingException("Invalid credentials !!!!!")).getAccounts();
//		if(optionalCust.isPresent())
//			return optionalCust.get().getAccounts();
//		throw new CustomerHandlingException("Invalid credentials !!!!!");
	}

	@Override
	public  Customer createAccount(Customer customer) {
	//	customer.getAccounts().forEach(a -> a.setAcctOwner(customer));
		return customerRepo.save(customer);
	}

}
