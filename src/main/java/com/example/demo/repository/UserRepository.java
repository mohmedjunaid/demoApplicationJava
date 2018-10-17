package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "select u from #{#entityName} u where u.email = :email or u.userName = :userName and u.status = true")
	User getUserByEmailOrName(@Param("email")String email, @Param("userName")String userName);

    @Query(value = "select u from #{#entityName} u where u.id = :id")
    User findById(@Param("id")Integer id);
    
    /*@Query(value = "select u from #{#entityName} u where u.email = :userName or u.userName = :userName and u.status = true")
    User findByUserNameOrEmail(@Param("userName")String userName);*/
}
