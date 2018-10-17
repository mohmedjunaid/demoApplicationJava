package com.example.demo.request;

public class ConfirmationRequest extends BaseRequest{
	private String token;
	private String otp;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
}
