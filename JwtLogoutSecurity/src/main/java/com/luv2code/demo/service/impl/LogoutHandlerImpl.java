package com.luv2code.demo.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.BlacklistedToken;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.BlacklistTokenService;
import com.luv2code.demo.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogoutHandlerImpl implements LogoutHandler {

	private final BlacklistTokenService tokenBlackListService;
	private final JwtService jwtService;
	private final UserRepository userRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader("Authorization");
		String jwt = null;
		String username = null;
		Instant expirationTime = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
			username = jwtService.extractUsername(jwt);
			expirationTime = jwtService.extractExpiration(jwt).toInstant();
		}
        
		Optional<User> user = userRepository.findByUsername(username);
		
		BlacklistedToken tokenBlackList = BlacklistedToken.builder().token(jwt).expire(expirationTime).user(user.get()).build();
		tokenBlackListService.save(tokenBlackList);

		handleLogoutSuccess(request, response);
	}
	
	private void handleLogoutSuccess(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
