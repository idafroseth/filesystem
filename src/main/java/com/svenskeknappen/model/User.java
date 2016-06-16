package com.svenskeknappen.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	
	public User() {

	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	public User(String username, String password, 
			boolean enabled, Set<UserRole> userRole) {
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.userRole = userRole;
		}

	// Getter and setter methods
	@Id
	@Column(name = "username", unique = true, 
		nullable = false, length = 45)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	@Column
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "password", 
			nullable = false, length = 60)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	
	public boolean hasRole(String role){
		for(UserRole r : this.userRole){
			if(role.equals(r.getRole())){
				return true;
			}
		}
		return false;
		
	}
}
