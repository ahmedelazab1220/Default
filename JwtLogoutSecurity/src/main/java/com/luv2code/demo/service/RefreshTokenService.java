package com.luv2code.demo.service;

import com.luv2code.demo.entity.RefreshToken;

public interface RefreshTokenService {

	RefreshToken createRefreshToken(String username);

	RefreshToken findByToken(String token);

	RefreshToken verifyExpiration(RefreshToken token);

	void deleteByEntity(RefreshToken refreshToken);

}
