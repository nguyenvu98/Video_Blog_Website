package com.poly.service.impl;

import java.util.List;

import com.poly.dao.UserDao;
import com.poly.dao.impl.UserDaoImpl;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao dao;
	
	public UserServiceImpl( ) {
		dao = new UserDaoImpl();
	}

	@Override
	public User findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}


	@Override
	public User update(User entity) {
		return dao.update(entity);
	}


	@Override
	public User resetPassword(String email) {
		return null;

	}

	@Override
	public User create(String username, String password, String email) {
		User entity = new User();
		entity.setUsername(username);
		entity.setPassword(password);
		entity.setEmail(email);
		entity.setIsAdmin(Boolean.FALSE);
		entity.setIsActive(Boolean.TRUE);
		return dao.create(entity);
	}

	@Override
	public User delete(String username) {
		User entity = findByUsername(username);
		entity.setIsActive(Boolean.FALSE);
		return dao.update(entity);
	}

}
