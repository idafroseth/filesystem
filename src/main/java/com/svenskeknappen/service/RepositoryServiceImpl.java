package com.svenskeknappen.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svenskeknappen.dao.UserDao;
import com.svenskeknappen.model.User;
import com.svenskeknappen.model.UserRole;

@Service("repositoryService")
public class RepositoryServiceImpl implements RepositoryService {
	@Autowired
	UserDao userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.save(user);
	}

	@Transactional
	public void deleteUser(String username) {
		String hqlRoles = "SELECT u from UserRole u " + "join u.user c " + "where c.username = :username";
		Query queryRoles = sessionFactory.getCurrentSession().createQuery(hqlRoles);
		queryRoles.setString("username", username);
		List<UserRole> usersRoles = queryRoles.list();
		for (UserRole r : usersRoles) {
			sessionFactory.getCurrentSession().delete(r);
		}
		userDao.delete(userDao.findByUsername(username));

	}

	@Transactional
	public void removeRoleFromUser(String username, String role) {
		// remove role from user
		User userInDb = userDao.findByUsername(username);
		Set<UserRole> roles = userInDb.getUserRole();
		if (roles != null) {
			roles.remove(role);

			// delete the role
			String hqlRoles = "SELECT u from UserRole u " + "join u.user c " + "where c.username = :username";
			Query queryRoles = sessionFactory.getCurrentSession().createQuery(hqlRoles);
			queryRoles.setString("username", username);
			List<UserRole> usersRoles = queryRoles.list();
			for (UserRole r : usersRoles) {
				roles.remove(r);
				sessionFactory.getCurrentSession().update(userInDb);
				sessionFactory.getCurrentSession().delete(r);
			}
		}
	}

}
