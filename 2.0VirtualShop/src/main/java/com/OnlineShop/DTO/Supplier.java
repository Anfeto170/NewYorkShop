package com.OnlineShop.DTO;

public class Supplier {
	
	private String NIT;
	private String Name;
	private String Address;
	private String Phone;
	private String Email;
	private String Website;
	
	public Supplier(String nIT, String name, String address, String phone, String email, String website) {
		super();
		NIT = nIT;
		Name = name;
		Address = address;
		Phone = phone;
		Email = email;
		Website = website;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}
	
	
	
}