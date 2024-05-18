package com.luv2code.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.demo.entity.BlacklistedToken;

@Repository
public interface TokenBlackListRepository extends JpaRepository<BlacklistedToken, Integer> {

	Optional<BlacklistedToken> findByToken(String token);

	List<BlacklistedToken> findAllByUserId(int theId);
}
