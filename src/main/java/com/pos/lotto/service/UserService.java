package com.pos.lotto.service;

import java.util.List;

import com.pos.lotto.model.User;

public interface UserService {

	public User findUserById(int id);

	public User findUserByEmail(String email);

	public void saveUser(User user);
	
	public void updateUser(User user);

	public List<User> findUserByCompany(String comapny);

	public List<User> findUserByRole(String role);
	
	public void firstLogin(User user) ;
}
