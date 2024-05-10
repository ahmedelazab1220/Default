package com.luv2code.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.demo.entity.User;

public interface UserService extends UserDetailsService {

	@Override
	UserDetails loadUserByUsername(String username);

	public List<User> findAll();

	public User findById(int theId);

	public User save(User user);

	public void deleteById(int theId);

}
