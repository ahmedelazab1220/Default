package com.luv2code.demo.service;

import com.luv2code.demo.entity.BlacklistedToken;

public interface BlacklistTokenService {

	Boolean findByToken(String token);

	void save(BlacklistedToken tokenBlackList);

}
