package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{
	
	Login findByUserIdAndToken(int userId, String token);
	
	@Query("select u from #{#entityName} u where u.userId.id = :userId")
	Login findbyUserId(@Param("userId")Integer userId);
	 
}
