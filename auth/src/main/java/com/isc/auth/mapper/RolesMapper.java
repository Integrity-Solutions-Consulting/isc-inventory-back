package com.isc.auth.mapper;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RoleDetailsResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.entitys.MenuRoleEntity;
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
	            entity.isActive(),
	            privileges
	        );
	    }
	 
	 public static RoleDetailsResponseDTO detailToDto(RolesEntity entity) {
		    if (entity == null) return null;

		    // Mapear privilegios
		    Set<PrivilegeResponseDTO> privileges = entity.getRolesPrivilegies().stream()
		            .map(PrivilegeRoleEntity::getPrivilege)
		            .map(PrivilegeMapper::toDto)
		            .collect(Collectors.toSet());

		    // Obtener menús como entidades
		    List<MenuEntity> menuEntities = entity.getRoleMenus().stream()
		            .map(MenuRoleEntity::getMenu)
		            .collect(Collectors.toList());
		    
		    // Construir árbol de menús
		    List<MenuResponseDTO> menuTree = MenuMapper.toDtoTree(menuEntities);
		    
		    return new RoleDetailsResponseDTO(
		        entity.getId(),
		        entity.getName(),
		        entity.isActive(),
		        privileges,
		        new LinkedHashSet<>(menuTree)
		    );
		}
}
