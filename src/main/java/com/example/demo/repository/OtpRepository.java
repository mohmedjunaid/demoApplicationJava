package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Otp;
import com.example.demo.entity.User;

import java.lang.String;
import java.util.List;

public interface OtpRepository extends JpaRepository<Otp, Integer>{
	
	Otp findByOtpAndUserId(String otp, User userId);
}
