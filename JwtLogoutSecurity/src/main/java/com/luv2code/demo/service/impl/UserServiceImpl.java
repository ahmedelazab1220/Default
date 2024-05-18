package com.luv2code.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.User;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.security.SecurityUser;
import com.luv2code.demo.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		return user.map(SecurityUser::new).get();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
