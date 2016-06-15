package com.svenskeknappen.service;


import java.util.List;

import com.svenskeknappen.model.User;

public interface RepositoryService {
	

	/**
	 * Fetch a list of all the users in the system
	 * @return
	 */
	public List<User> getAllUsers();
	
	/**
	 * Persist a user
	 */
	public void addUser(User user);
	
	/**
	 * Delete a user
	 */
	public void deleteUser(String username);
	
	
	public void removeRoleFromUser(String username, String role);
	
	
	
}
