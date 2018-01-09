package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name="otp", nullable = false)
    private String otp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id" , nullable = false)
    private User userId;
	
	@Column(name="verified", nullable = false)
    private Boolean verified;
	
	@Column(name="created", nullable = false)
    private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
