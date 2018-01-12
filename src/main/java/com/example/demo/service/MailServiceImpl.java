package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Email;
import com.example.demo.repository.EmailRepository;
import com.example.demo.util.EmailUtil;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Override
	public boolean sendOtp(String email, String otpText) {
		Email email2=emailRepository.findByPerpose(1);
		final String fromEmail = email2.getEmail();
		final String password = email2.getPassword();
		final String toEmail = email; // can be any email id

		
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", email2.getHost()); // SMTP Host
		props.put("mail.smtp.port", email2.getPort()); // TLS Port
		props.put("mail.smtp.auth", email2.getAuth()); // enable authentication
		props.put("mail.smtp.starttls.enable", email2.getStarttls()); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		return EmailUtil.sendEmail(session, toEmail, "eshop otp", "your OTP is " + otpText);
	}

}