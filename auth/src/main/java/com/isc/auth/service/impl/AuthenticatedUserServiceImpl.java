package com.isc.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.isc.auth.entitys.UserEntity;
import com.isc.auth.repository.UserRepository;
import com.isc.auth.service.AuthenticatedUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {
	private final UserRepository repository;

	public UserEntity getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		return repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
	}

	public boolean isAdmin(UserEntity user) {
		return user.getUserRoles().stream().anyMatch(role -> role.getRole().getName().equals("ADMIN"));
	}

	public boolean isSelfOrAdmin(UserEntity currentUser, Integer targetUserId) {
		return isAdmin(currentUser) || currentUser.getId().equals(targetUserId);
	}
	
	public boolean isSelf(UserEntity currentUser, Integer targetUserId) {
		return currentUser.getId().equals(targetUserId);
	}
}
