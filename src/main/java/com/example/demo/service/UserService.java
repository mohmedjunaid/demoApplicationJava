package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.RegistrationResponse;

@Service
public interface UserService {

	RegistrationResponse registerUser(RegistrationRequest registrationRequest, String origin);

}
