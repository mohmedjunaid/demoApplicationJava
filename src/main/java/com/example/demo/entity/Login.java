package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id" , nullable = false, unique = true)
    private User userId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private Role role;
	
	@Column(name="password" , nullable = false)
    private String password;
	
	@Column(name="last_login" , nullable = false)
    private Date lastLogin;
	
	@Column(name="token" , nullable = false, unique = true)
    private String token;
	
	@Column(name="active" , nullable = false)
    private Boolean active;

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
