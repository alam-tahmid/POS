package com.pos.lotto.service.Impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.Role;
import com.pos.lotto.model.User;
import com.pos.lotto.repository.RoleRepository;
import com.pos.lotto.repository.UserRepository;
import com.pos.lotto.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setFirstLogin(1);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	
	@Override
	public void updateUser(User user) {
		
		userRepository.save(user);
	}

	@Override
	public List<User> findUserByCompany(String comapny) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(int id) {

		return userRepository.findById(id);
	}
	
	@Override
	public void firstLogin(User user) {
		// TODO Auto-generated method stub

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setFirstLogin(0);
		userRepository.save(user);
	}

}
