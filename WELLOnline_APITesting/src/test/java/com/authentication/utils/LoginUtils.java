package com.authentication.utils;

import java.io.Serializable;

public class LoginUtils implements Serializable{

	
	private static final long serialVersionUID = 1L;
	public String email;
	public String password;
	public String getemail() {
		return email;
	}
	public void setemail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}