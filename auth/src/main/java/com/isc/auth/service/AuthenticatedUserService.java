package com.isc.auth.service;

import com.isc.auth.entitys.UserEntity;

public interface AuthenticatedUserService {
	
	public UserEntity getAuthenticatedUser();
	public boolean isAdmin(UserEntity user);
	public boolean isSelfOrAdmin(UserEntity currentUser, Integer targetUserId);
	
}
