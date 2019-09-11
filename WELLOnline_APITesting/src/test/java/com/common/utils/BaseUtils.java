package com.common.utils;

import java.io.Serializable;

public class BaseUtils implements Serializable{

	
	private static final long serialVersionUID = 1L;
	public String email;
	public String password;
	public String token;
	public int id;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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