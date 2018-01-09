package com.example.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Otp;
import com.example.demo.entity.ResponseStatus;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.request.ConfirmationRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.request.VerificationRequest;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.ConfirmationResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.RegistrationResponse;
import com.example.demo.response.VerificationResponse;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public RegistrationResponse registerUser(RegistrationRequest registrationRequest , String origin) {
		RegistrationResponse response = new RegistrationResponse();
		if (registrationRequest != null && registrationRequest.getEmail() != null
				&& registrationRequest.getUserName() != null) {
			User registerUser = userRepository.getUserByEmailOrName(registrationRequest.getEmail(),
					registrationRequest.getUserName());
			if (registerUser == null) {
				User user = new User();
				String testDate = registrationRequest.getBirthDate();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = formatter.parse(testDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				user.setDateOfBirth(date);
				user.setEmail(registrationRequest.getEmail());
				user.setFirstName(registrationRequest.getFirstName());
				user.setLastName(registrationRequest.getLastName());
				user.setMiddleName(registrationRequest.getMiddleName());
				/* user.setProfile(registrationRequest.getProfile()); */
				user.setStatus(true);
				user.setActivated(false);
				user.setSex(registrationRequest.getGender());
				user.setUserName(registrationRequest.getUserName());
				userRepository.save(user);

				UserRole userRole = new UserRole();
				userRole.setRoleAssigned(new Date());
				userRole.setRole(Role.USER);
				userRole.setUserId(user);
				userRoleRepository.save(userRole);
				
				loginService.createOtp(user.getEmail());
				
				String authToken = authTokenService.generateToken(origin, user.getId(), Role.USER, registrationRequest.getPassword());
				
				response.setToken(authToken);
				response.setUserId(user.getId());
				response.setEmail(user.getEmail());
				response.setAlreadyRegistered(false);
				response.setStatus(ResponseStatus.SUCCESS);
				
				return response;
			} else {
				List<String> message = new ArrayList<>();
				message.add(messageSource.getMessage("user.already.registered", null, null));
				response.setMessages(message);
				response.setAlreadyRegistered(true);
				response.setStatus(ResponseStatus.FAILURE);
				return response;
			}
		} else {
			List<String> message = new ArrayList<>();
			message.add(messageSource.getMessage("request.null", null, null));
			response.setMessages(message);
			response.setStatus(ResponseStatus.FAILURE);
			return response;
		}
	}

	@Override
	public ConfirmationResponse confirmation(ConfirmationRequest confirmationRequest) {
		System.out.println("service");
		return null;
	}

	@Override
	public VerificationResponse verification(VerificationRequest verificationRequest) {
		VerificationResponse response=new VerificationResponse();
		System.out.println("servicess");
	
		response.setStatus(ResponseStatus.SUCCESS);
		return response;
	}
	
}
