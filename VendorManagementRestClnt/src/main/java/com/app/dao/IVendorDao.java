package com.app.dao;

import java.util.List;

import com.app.pojos.Vendor;

public interface IVendorDao {
	//Add a method for user authentication
	Vendor authenticateUser(String email, String pwd);
	//Add a method for listing all vendors
	List<Vendor> getAllVendors();
	//add method to delete vendor details
	String deleteVendorDetails(Vendor persistentVendor);
	//add method to get vendor details 
	Vendor getVendorDetails(int vendorId);
	//add a method for vendor registration
	String registerVendor(Vendor transientVendor);
	
}
