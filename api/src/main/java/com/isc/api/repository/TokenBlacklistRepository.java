package com.isc.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isc.api.entitys.TokenBlacklistEntity;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklistEntity, Integer> {
	Optional<TokenBlacklistEntity> findByToken(String token);
}