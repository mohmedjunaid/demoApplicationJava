package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.entity.Otp;
import com.example.demo.entity.ResponseStatus;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.OtpRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.util.OtpGenerator;

@Service
@Component
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthTokenService authTokenService;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private OtpRepository otpRepository;
	
	@Override
	public LoginResponse loginUser(LoginRequest loginRequest, String origin) {
		LoginResponse loginResponse = new LoginResponse();
		User user = userRepository.getUserByEmailOrName(loginRequest.getUserName(), loginRequest.getUserName());
		if (user == null) {
			List<String> message = new ArrayList<>();
			message.add(messageSource.getMessage("user.not.found", null, null));
			loginResponse.setMessages(message);
			loginResponse.setStatus(ResponseStatus.FAILURE);
			return loginResponse;
		}
		System.out.println("user" + user.getId());
		Login login = loginRepository.findbyUserId(user.getId());
		
		if (login.getPassword().equals(loginRequest.getPassword())) {
			String authToken = authTokenService.generateTokenLogin(origin, user.getId(), login);
			loginResponse.setUserId(user.getId());
			loginResponse.setRole(login.getRole().name());
			loginResponse.setToken(authToken);
			loginResponse.setActive(user.getActivated());
			loginResponse.setEmail(user.getEmail());
			loginService.createOtp(user.getEmail());
			loginResponse.setStatus(ResponseStatus.SUCCESS);
			return loginResponse;
		} else {
			List<String> message = new ArrayList<>();
			message.add(messageSource.getMessage("password.not.matched", null, null));
			loginResponse.setMessages(message);
			loginResponse.setStatus(ResponseStatus.FAILURE);
			return loginResponse;
		}
	}

	@Override
	public void createOtp(String email) {

		User user = userRepository.getUserByEmailOrName(email, email);

		if (user != null) {
			char[] otpChar = OtpGenerator.geek_Password(4);
			String otpText=String.valueOf(otpChar);
			boolean otpSent = mailService.sendOtp(email, otpText);

			if (otpSent == true) {
				Otp otp = new Otp();
				otp.setOtp(otpText);
				otp.setUserId(user);
				otp.setVerified(false);
				Date created = new Date();
				otp.setCreated(created);
				otpRepository.save(otp);
			}
		}
	}
}
