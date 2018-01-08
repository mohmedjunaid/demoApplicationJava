package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.ApiRestController;
import com.example.demo.request.ConfirmationRequest;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.response.ConfirmationResponse;
import com.example.demo.response.RegistrationResponse;
import com.example.demo.service.UserService;

@ApiRestController
public class UserController extends BaseController{
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest,@RequestHeader(value = "Origin", required = false) String origin) {
		return new ResponseEntity<RegistrationResponse>(userService.registerUser(registrationRequest,origin), HttpStatus.OK);
	}
	
	@PostMapping("/confirmation")
	public ResponseEntity<ConfirmationResponse> confirmation(@RequestBody ConfirmationRequest confirmationRequest) {
		System.out.println("gg");
		return new ResponseEntity<ConfirmationResponse>(userService.confirmation(confirmationRequest), HttpStatus.OK);
	}
}