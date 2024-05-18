package com.luv2code.demo.service;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.LoginResponse;
import com.luv2code.demo.entity.User;

public interface AuthenticationService {

	public LoginResponse login(LoginRequest loginRequest);

	public User signUp(RegisterRequest registerRequest);

}
