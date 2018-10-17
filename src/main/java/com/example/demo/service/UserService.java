package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.request.ConfirmationRequest;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.request.VerificationRequest;
import com.example.demo.response.ConfirmationResponse;
import com.example.demo.response.RegistrationResponse;
import com.example.demo.response.VerificationResponse;

@Service
public interface UserService {

	RegistrationResponse registerUser(RegistrationRequest registrationRequest, String origin);

	ConfirmationResponse confirmation(ConfirmationRequest confirmationRequest);

	VerificationResponse verification(VerificationRequest verificationRequest);

}
