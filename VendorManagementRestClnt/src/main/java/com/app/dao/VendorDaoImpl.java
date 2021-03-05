package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Location;
import com.app.pojos.Role;
import com.app.pojos.Vendor;

@Repository // to tell SC about DAO layer bean + enables exc translation mechanism(catches
			// DB related
//excs n re throws them as spring related exceptions (un checked)
public class VendorDaoImpl implements IVendorDao {
	// dependency : SF
	@Autowired // byType
	// OR JPA specific anno : @PersistenceContext
	private EntityManager mgr;

	@Override
	public Vendor authenticateUser(String email, String pwd) {
		String jpql = "select v from Vendor v where v.email=:em and v.password=:pass";
		return mgr.createQuery(jpql, Vendor.class).setParameter("em", email).setParameter("pass", pwd)
				.getSingleResult();
	}

	@Override
	public List<Vendor> getAllVendors() {
		String jpql = "select v from Vendor v where v.userRole=:role";
		return mgr.createQuery(jpql, Vendor.class).setParameter("role", Role.VENDOR).getResultList();
	}

	@Override
	public String deleteVendorDetails(Vendor persistentVendor) {
		// since there exists one-to-one uni dir asso between Vendor n Location , & PK
		// of locations tbl is FK which
		// refs PK of vendors PK , You CAN NOT directly delete a parent rec(i.e rec in
		// vendors table) w/o first deleting
		// a child rec(i.e rec in locations table) : o.w throws ConstraintViolation
		// Exception
		// chk if location is assgned or not
		Location location = mgr.find(Location.class, persistentVendor.getId());
		if (location != null) {
			mgr.remove(location);
			mgr.remove(persistentVendor);
			return "Vendor n location details deleted for ID=" + persistentVendor.getId();
		}
		mgr.remove(persistentVendor);
		return "Vendor  details deleted for ID=" + persistentVendor.getId();
		
	}

	@Override
	public Vendor getVendorDetails(int vendorId) {
		// TODO Auto-generated method stub
		return mgr.find(Vendor.class, vendorId);
	}

	@Override
	public String registerVendor(Vendor transientVendor) {
		mgr.persist(transientVendor);
		return "Vendor details reged successfully with ID "+transientVendor.getId();
	}
	

}
