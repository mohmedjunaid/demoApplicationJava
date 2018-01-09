package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.util.EmailUtil;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Service
public class MailServiceImpl implements MailService {

	@Override
	public boolean sendOtp(String email, String otpText) {
		final String fromEmail = "junaid.mansuri@indianic.com";
		final String password = "";
		final String toEmail = "junaid.mansuri@indianic.com"; // can be any email id

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

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