package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name="email" , nullable = false)
    private String email;
	
	@Column(name="password" , nullable = false)
    private String password;
	
	@Column(name="host" , nullable = false)
    private String host;
	
	@Column(name="port" , nullable = false)
    private String port;
	
	@Column(name="auth" , nullable = false)
    private Boolean auth;
	
	@Column(name="starttls" , nullable = false)
    private Boolean starttls;

	@Column(name="perpose" , nullable = false)
    private Integer perpose;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Boolean getStarttls() {
		return starttls;
	}

	public void setStarttls(Boolean starttls) {
		this.starttls = starttls;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public Integer getPerpose() {
		return perpose;
	}

	public void setPerpose(Integer perpose) {
		this.perpose = perpose;
	}

}
