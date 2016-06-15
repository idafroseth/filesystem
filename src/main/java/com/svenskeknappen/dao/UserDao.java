package com.svenskeknappen.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.svenskeknappen.model.User;

@Transactional
public interface UserDao {

	public boolean save(User user);
	public User findByUsername(String username);
	public List<User> findAll();
	
	public void delete(User user);
}
