package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.VerificationRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.VerificationResponse;


public interface LoginService {

	LoginResponse loginUser(LoginRequest loginRequest, String origin);

	boolean createOtp(String email);

	VerificationResponse verifyOtp(VerificationRequest verificationRequest);
}
