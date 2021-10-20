package com.OnlineShop.DTO;

public class User {

	private String IDcard, Name, Email, User, Password;

	public User(String iDcard, String name, String email, String user, String password) {
		super();
		IDcard = iDcard;
		Name = name;
		Email = email;
		User = user;
		Password = password;
	}


	@Override
	public String toString() {
		return "User [IDcard=" + IDcard + ", Name=" + Name + ", Email=" + Email + ", User=" + User + ", Password="
				+ Password + "]";
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
