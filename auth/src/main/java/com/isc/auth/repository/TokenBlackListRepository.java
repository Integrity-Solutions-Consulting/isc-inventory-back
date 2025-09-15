package com.isc.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isc.auth.entitys.TokenBlackListEntity;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackListEntity, Integer> {
	Optional<TokenBlackListEntity> findByToken(String token);
}
