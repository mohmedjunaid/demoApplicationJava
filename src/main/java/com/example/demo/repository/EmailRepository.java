package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Email;
import java.lang.Integer;

public interface EmailRepository extends JpaRepository<Email, Integer>{
	
	Email findByPerpose(Integer perpose);
}
