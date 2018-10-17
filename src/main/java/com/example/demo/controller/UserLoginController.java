package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.annotation.ApiRestController;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.VerificationResponse;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

@ApiRestController
public class UserLoginController extends BaseController{
	
	private LoginService loginService;
	
	@Autowired
	public UserLoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> logindemo(@RequestBody LoginRequest loginRequest,@RequestHeader(value = "Origin", required = false) String origin) {
		System.out.println("finallly"+loginRequest.toString());
		LoginResponse loginResponse  = loginService.loginUser(loginRequest,origin);
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
	}
	
	@GetMapping("/resendOtp")
	public ResponseEntity<VerificationResponse> resendOtp(@RequestParam(name="email") String email) {
		return new ResponseEntity<VerificationResponse>(loginService.resendOtp(email), HttpStatus.OK);
	}
}
