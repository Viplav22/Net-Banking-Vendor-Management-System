package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IVendorDao;
import com.app.pojos.Role;
import com.app.pojos.Vendor;

@Service//to tell SC class is a spring bean containing B.L 
@Transactional //to tell SC , to automate tx management 
//(by spring supplied hib tx mgr bean : o.s.orm.hibernate5.HibernateTransactionManager
public class VendorServiceImpl implements IVendorService {
	//dependency : DAO layer i/f
	@Autowired
	private IVendorDao vendorDao;

	@Override
	public Vendor authenticateUser(String email, String pwd) {
		// TODO Auto-generated method stub
		return vendorDao.authenticateUser(email, pwd);
	}

	@Override
	public List<Vendor> getAllVendors() {
		// TODO Auto-generated method stub
		return vendorDao.getAllVendors();
	}

	@Override
	public String deleteVendorDetails(int vendorId) {
		Vendor v=vendorDao.getVendorDetails(vendorId);
		if(v != null)
			return vendorDao.deleteVendorDetails(v);
		return "Vendor details deletion failed";
	}

	@Override
	public String registerVendor(Vendor transientVendor) {
		//assign vendor role 
		transientVendor.setUserRole(Role.VENDOR);		
		return vendorDao.registerVendor(transientVendor);
	}
	
	

}
