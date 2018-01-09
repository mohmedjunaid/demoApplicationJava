package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.request.LoginRequest;
import com.example.demo.response.LoginResponse;


public interface LoginService {

	LoginResponse loginUser(LoginRequest loginRequest, String origin);

	void createOtp(String email);
}
