package com.luv2code.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.BlacklistedToken;
import com.luv2code.demo.repo.TokenBlackListRepository;
import com.luv2code.demo.service.BlacklistTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlacklistTokenServiceImpl implements BlacklistTokenService {

	private final TokenBlackListRepository tokenBlackListRepository;

	@Override
	public Boolean findByToken(String token) {
		Optional<BlacklistedToken> tokenBlackList = tokenBlackListRepository.findByToken(token);

		return (tokenBlackList.isEmpty() ? true : false);
	}

	@Override
	public void save(BlacklistedToken tokenBlackList) {
		tokenBlackListRepository.save(tokenBlackList);
	}

}
