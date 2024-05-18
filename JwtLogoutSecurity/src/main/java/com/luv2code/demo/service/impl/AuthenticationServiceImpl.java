package com.luv2code.demo.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2code.demo.dto.mapper.SystemMapper;
import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.LoginResponse;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.AuthenticationService;
import com.luv2code.demo.service.JwtService;
import com.luv2code.demo.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final SystemMapper mapper;
	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;

	@Override
	public LoginResponse login(LoginRequest loginRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		String jwtToken = jwtService.generateToken(loginRequest.getUsername());

		LoginResponse loginResponse = mapper.loginRequestToLoginResponse(loginRequest);
		loginResponse.setAccessToken(jwtToken);
		loginResponse.setRefreshToken(refreshTokenService.createRefreshToken(loginRequest.getUsername()).getToken());

		return loginResponse;
	}

	@Override
	public User signUp(RegisterRequest registerRequest) {
		User user = mapper.registerRequstToUser(registerRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
