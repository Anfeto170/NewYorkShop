package com.OnlineShop.DTO;

public class Costumer {

	//?iDcard=333&name=noseja&address=Bogota&phone=789456&email=lisabe791
	private String IDcard, Name, Address, Phone, Email;

	public Costumer(String iDcard, String name, String address, String phone, String email) {
		super();
		IDcard = iDcard;
		Name = name;
		Address = address;
		Phone = phone;
		Email = email;
	}

	public String getIDcard() {
		return IDcard;
	}

	public void setIDcard(String iDcard) {
		IDcard = iDcard;
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

}
