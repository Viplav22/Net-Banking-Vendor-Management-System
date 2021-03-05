package com.app.pojos;

import javax.persistence.*;

//Location POJO : locationId(PK : shared PK ), city,state , country
@Entity
@Table(name="locations_tbl")
public class Location extends BaseEntity{
	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 20)
	private String country;
	//uni dir one to one asso with Vendor
	@OneToOne
	@JoinColumn(name="vendor_id")
	//w/o @MapsId : there would be a separate PK for the locations tbl (i.e 5 cols : id , city,state,country,vendor_id)
	//How to tel hibernate to create a shared PK (shared with vendors table ) : @MapsId
	@MapsId //cols : vendor_id(shared PK n FK referencing PK of the vendors table),city,state , country
	private Vendor currentVendor;
	public Location() {
		// TODO Auto-generated constructor stub
	}
	public Location(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Vendor getCurrentVendor() {
		return currentVendor;
	}
	public void setCurrentVendor(Vendor currentVendor) {
		this.currentVendor = currentVendor;
	}
	@Override
	public String toString() {
		return "Location [city=" + city + ", state=" + state + ", country=" + country + "]";
	}
	
	
}
