package com.svenskeknappen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(
		columnNames = { "role", "username" }))
public class UserRole {
	
	public UserRole(){
		
	}
	
	private Integer userRoleId;
	private User user;
	private String role;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id", 
		unique = true, nullable = false)
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(nullable = false, length = 45)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
