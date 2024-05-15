package com.luv2code.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.LoginResponse;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

	private final AuthenticationService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> signUp(@RequestBody RegisterRequest registerRequest){
		  System.out.println("SignUp User");
		  return new ResponseEntity<>(userService.signUp(registerRequest) , HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		  return new ResponseEntity<>(userService.login(loginRequest) , HttpStatus.ACCEPTED);
	}

}
