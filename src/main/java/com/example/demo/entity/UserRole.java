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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
    private User userId;
	
	@Column(name="role_assigned")
    private Date roleAssigned;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getRoleAssigned() {
		return roleAssigned;
	}

	public void setRoleAssigned(Date roleAssigned) {
		this.roleAssigned = roleAssigned;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
