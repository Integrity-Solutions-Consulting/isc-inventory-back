package com.isc.auth.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.dto.response.UserRegisterResponseDTO;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;

public class UserRegisterMapper {
	 public static UserRegisterResponseDTO toDto(UserEntity entity, String password) {
	        if (entity == null) return null;

	        Set<RolesResponseDTO> roles = entity.getUserRoles().stream()
	                .map(UserRoleEntity::getRole)
	                .map(RolesMapper::toDto)
	                .collect(Collectors.toSet());
	        Set<PrivilegeResponseDTO> privileges = entity.getUserPrivilegies().stream()
	                .map(PrivilegeUserEntity::getPrivilege)
	                .map(PrivilegeMapper::toDto)
	                .collect(Collectors.toSet());

	        return new UserRegisterResponseDTO(
	                entity.getId(),
	                entity.getUsername(),
	                entity.getEmail(),
	                entity.getFirstNames(),
	                entity.getEmployeeId(),
	                entity.isLoggedIn(),
	                entity.isActive(),
	                entity.isSuspended(),
	                roles,
	                privileges,
	                password
	        );
	    }
}
