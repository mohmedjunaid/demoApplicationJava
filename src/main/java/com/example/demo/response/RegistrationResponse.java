package com.example.demo.response;

public class RegistrationResponse extends BaseResponse{
	private Boolean alreadyRegistered;
	private Integer userId;
	private String email;
	private String token;
	public Boolean getAlreadyRegistered() {
		return alreadyRegistered;
	}
	public void setAlreadyRegistered(Boolean alreadyRegistered) {
		this.alreadyRegistered = alreadyRegistered;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
