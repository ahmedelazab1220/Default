package com.luv2code.demo.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.RefreshToken;
import com.luv2code.demo.exc.custom.NotFoundRefreshTokenException;
import com.luv2code.demo.exc.custom.RefreshTokenExpiredException;
import com.luv2code.demo.repo.RefreshTokenRepository;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${security.jwt.refresh-token.expiration-time}")
	private long refreshTokenExpiration;

	@Override
	public RefreshToken createRefreshToken(String username) {
		
		RefreshToken refreshToken = RefreshToken.builder().user(userRepository.findByUsername(username).get())
				// set expire of refresh token to 10 days you can configure it
				// application.properties file
				.token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
				.build();


		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public RefreshToken findByToken(String token) {
		Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);

		if (refreshToken.isEmpty()) {
			throw new NotFoundRefreshTokenException("Not Found Token OR Revoked!");
		}

		return refreshToken.get();
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RefreshTokenExpiredException(
					token.getToken() + " Refresh token is expired. Please make a new login..!");
		}
		return token;
	}

	@Override
	public void deleteByEntity(RefreshToken refreshToken) {
		refreshTokenRepository.delete(refreshToken);
	}

}
