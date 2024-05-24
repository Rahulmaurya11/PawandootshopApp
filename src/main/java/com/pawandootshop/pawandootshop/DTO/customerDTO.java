package com.pawandootshop.pawandootshop.DTO;

public class customerDTO {
	
	private String Name;
	private String LastName;
	private String UserName; 
	private String Contactnumber;
    private String password;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getContactnumber() {
		return Contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		Contactnumber = contactnumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	


}
