package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.app.dto.BankAccount;
import com.app.dto.BankAccounts;

@Service
// here we have not added @Transactional because transaction will happen in backend and this is frontend
public class NetbankingServiceImpl implements INetbankingService {
	// dependency : RestTemplate
	// Front end'd service layer is dependent upon RestTemplate , for making Rest
	// call to the back end
	private RestTemplate template;
	@Value("${REST_GET_ACCTS}") // SpEL : for getting the url of backend : set in application.properties : for
								// getting bank acct details
	private String getURL;//

	@Value("${REST_POST_ACCTS}")
	private String postURL;

	// constr based D.I
	@Autowired
	public NetbankingServiceImpl(RestTemplateBuilder builder) {
		template = builder.build();
	}

	@Override
	public List<BankAccount> authenticateCustomer(long custId, String pwd) {
		System.out.println(getURL);
		try {
			ResponseEntity<BankAccounts> respEntity = template.getForEntity(getURL, BankAccounts.class, custId, pwd);
			System.out.println("resp ent " + respEntity);
			return respEntity.getBody().getAccounts();
		} catch (HttpClientErrorException e) {
			System.out.println(e);
			return null;
		}

	}

	@Override
	public BankAccount createAccount(BankAccount acct) {
		System.out.println("in service : cr a/c " + postURL);
		try {
			// make a REST call : POST
			ResponseEntity<BankAccount> responseEntity = template.postForEntity(postURL, acct, BankAccount.class);
			return responseEntity.getBody();
		} catch (RestClientException e) {
			System.out.println("rest clnt error " + e);
		}
		return null;
	}

}
