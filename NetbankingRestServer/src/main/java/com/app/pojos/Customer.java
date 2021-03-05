package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//id , name,password
@Entity
@Table(name = "bank_customers")
public class Customer {
	@Id // => PK since there is no @GeneratedValue => assigned id
	private Long customerId;
	@Column(length = 20)
	@NotBlank(message = "Name is required")
	@Length(min = 3, max = 15, message = "Invalid len of Name")
	private String name;
	@Column(length = 20, nullable = false)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	private String password;
	@OneToMany(mappedBy = "acctOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	//Jackson : vendor for JSON API 
	//To tell serilizer n de-serializer(java---> json , json ---> java), done auto by SC , using Jackson APIs
	//to ignore the asso properties added in bi dir asso.
	@JsonIgnoreProperties("acctOwner")
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
