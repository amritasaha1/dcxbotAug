package com.amazonaws.lambda.demo;

public class Slots {
	
	private String customertype;
    private String policynumber;
	private String address;
	private String city;
	private String emailaddress;
	private String usa;
	private String pincode;
	private String street;
		
	
	public Slots(String customertype, String policynumber, String address, String city, String emailaddress, String usa, String pincode, String street) {
		this.customertype = customertype;
		this.policynumber = policynumber;
		this.address = address;
		this.city = city;
		this.emailaddress = emailaddress;
		this.usa = usa;
		this.pincode = pincode;
		this.street = street;
	}

	

	public String getPincode() {
		return pincode;
	}



	public void setPincode(String pincode) {
		this.pincode = pincode;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getUsa() {
		return usa;
	}



	public void setUsa(String usa) {
		this.usa = usa;
	}



	public String getCustomertype() {
		return customertype;
	}


	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}


	public String getPolicynumber() {
		return policynumber;
	}


	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmailaddress() {
		return emailaddress;
	}


	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	
	

}
