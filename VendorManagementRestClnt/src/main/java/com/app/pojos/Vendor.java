package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

//name,email,password,regAmount,regDate(LocalDate)
@Entity
@Table(name = "vendors")
public class Vendor extends BaseEntity {
	@Column(length = 20)
	@NotBlank(message = "Name is required")
	@Length(min = 3, max = 15, message = "Invalid len of Name")
	private String name = "abc";
	@Column(length = 20, unique = true)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email = "abc@gmail.com";
	@Column(length = 20, nullable = false)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	private String password;
	@Column(name = "reg_amt")
	@Min(value = 500)
	@Max(value = 5000)
	private double regAmount;
	@Column(name = "reg_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate regDate;
	// add user's role
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", length = 20)
	private Role userRole;

	// one to one mapping between Entity (Vendor) ----> Value Type (PaymentDetails)
	@Embedded // optional annotation (added only for understanding purpose)
	@Valid
	private PaymentDetails details;
	// one to many uni dir mapping between Vendor(Entity) 1 -----> * String(Phone
	// no)
	// how to tell hibernate : whatever follows is collection of basic value types
	@ElementCollection // MANDATORY . o.w hibernate throws mapping exc.
	// how to specify name of the collection table n FK col name
	// optional BUT recommended for clarity
	@CollectionTable(name = "vendor_phones", joinColumns = @JoinColumn(name = "vendor_id"))
	@Column(name = "phone_no", length = 10, unique = true)
	private List<String> phoneNos = new ArrayList<>();
	// one to many uni dir asso between Entity(Vendor) 1----->* VendorService (value
	// type)
	// collection of composite (embeddable) types
	@ElementCollection
	@CollectionTable(name = "vendor_services", joinColumns = @JoinColumn(name = "v_id"))
	private List<VendorService> services = new ArrayList<>();

	public Vendor() {
		System.out.println("in vendor ctor");
	}

	public Vendor(String name, String email, String password, double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public PaymentDetails getDetails() {
		return details;
	}

	public void setDetails(PaymentDetails details) {
		this.details = details;
	}

	public List<String> getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(List<String> phoneNos) {
		this.phoneNos = phoneNos;
	}

	public List<VendorService> getServices() {
		return services;
	}

	public void setServices(List<VendorService> services) {
		this.services = services;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "Vendor [name=" + name + ", email=" + email + ", regAmount=" + regAmount + ", regDate=" + regDate
				+ " role=" + userRole + ", getId()=" + getId() + "]";
	}
}
