package com.luv2code.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.demo.entity.User;

public interface UserService extends UserDetailsService {

	@Override
	UserDetails loadUserByUsername(String username);

	List<User> findAll();

}
