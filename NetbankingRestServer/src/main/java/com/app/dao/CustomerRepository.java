package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//add a method to find a customer by it's customerId n password
	@Query("select c from Customer c left outer join fetch c.accounts where c.customerId=:id and c.password = :pass")
	Optional<Customer> fetchDetailsCustomerIdAndPassword(@Param("id") long custId, @Param("pass") String pwd);
}
