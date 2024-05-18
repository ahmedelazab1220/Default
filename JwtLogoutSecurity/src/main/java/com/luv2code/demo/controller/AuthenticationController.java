package com.luv2code.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RefreshTokenRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.JwtResponse;
import com.luv2code.demo.dto.response.LoginResponse;
import com.luv2code.demo.entity.RefreshToken;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.service.AuthenticationService;
import com.luv2code.demo.service.JwtService;
import com.luv2code.demo.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

	private final AuthenticationService userService;
	private final RefreshTokenService refreshTokenService;
	private final JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<User> signUp(@RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok(userService.signUp(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}

	@PostMapping("/refreshToken")
	public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		RefreshToken refresh_token = refreshTokenService
				.verifyExpiration(refreshTokenService.findByToken(refreshTokenRequest.getRefresh_token()));

		User user = refresh_token.getUser();

		String access_token = jwtService.generateToken(user.getUsername());

		JwtResponse response = new JwtResponse(access_token, refresh_token.getToken());

		return response;
	}

}
