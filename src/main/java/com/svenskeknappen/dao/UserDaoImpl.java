package com.svenskeknappen.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.svenskeknappen.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(){
		
	}

	@Override
	public boolean save(User user) {
		if (findByUsername(user.getUsername()) ==null && !user.getUsername().isEmpty() && !user.getPassword().isEmpty()){
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM User ORDER by username DESC").list();
	}
	
	@Override
	public void delete(User user){
		try {
			if (user == null) {
				System.out.println("The deleted degree did not exist");
			}
			sessionFactory.getCurrentSession().delete(user);
		} catch (RuntimeException e) {
			System.out.println("Attached failed " + e);

		}
		
	}
	


}
