package com.pawandootshop.pawandootshop.DTO;


public class responseDTO {

	private boolean status;
	private String message;
	private String token;
	private long expiresin;
	private String username;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresin() {
		return expiresin;
	}

	public void setExpiresin(long expiresin) {
		this.expiresin = expiresin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
