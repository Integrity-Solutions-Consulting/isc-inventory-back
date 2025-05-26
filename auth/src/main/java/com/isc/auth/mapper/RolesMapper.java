package com.isc.auth.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserRoleEntity;

public class RolesMapper {
	 public static RolesResponseDTO toDto(RolesEntity entity) {
	        if (entity == null) return null;
	        Set<PrivilegeResponseDTO> privileges = entity.getRolesPrivilegies().stream()
	                .map(PrivilegeRoleEntity::getPrivilege)
	                .map(PrivilegeMapper::toDto)
	                .collect(Collectors.toSet());
	        return new RolesResponseDTO(
	            entity.getId(),
	            entity.getName(),
	            privileges
	        );
	    }
}
