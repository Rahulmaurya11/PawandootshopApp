package com.pawandootshop.pawandootshop.DTO;

public class JwtRequest {
	
	private String email;

	private String Password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
return this.Password;}
	

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String email, String password) {
		super();
		this.email = email;
		Password = password;
	}
	

	
}



