package com.isc.auth.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RoleDetailsResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.dto.response.UserDetailsResponseDTO;
import com.isc.auth.dto.response.UserLoginResponseDTO;
import com.isc.auth.dto.response.UserResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.entitys.MenuRoleEntity;
import com.isc.auth.entitys.MenuUserEntity;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;

public class UserMapper {
	public static UserResponseDTO toDto(UserEntity entity) {
		if (entity == null)
			return null;

		Set<RolesResponseDTO> roles = entity.getUserRoles().stream().filter(UserRoleEntity::isActive) // Solo activos
				.map(UserRoleEntity::getRole).map(RolesMapper::toDto).collect(Collectors.toSet());

		Set<PrivilegeResponseDTO> privileges = entity.getUserPrivilegies().stream()
				.filter(PrivilegeUserEntity::getActive) // Solo activos
				.map(PrivilegeUserEntity::getPrivilege).map(PrivilegeMapper::toDto).collect(Collectors.toSet());

		Set<MenuResponseDTO> menus = entity.getUserMenus().stream().filter(MenuUserEntity::getActive) // Solo activos
				.map(MenuUserEntity::getMenu).map(MenuMapper::toDto).collect(Collectors.toSet());
		
		return new UserResponseDTO(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getFirstNames(),
				entity.getEmployeeId(), entity.getLastModificationDate(), entity.getLastConnection(),
				entity.isLoggedIn(), entity.isActive(), entity.isSuspended(), roles, privileges, menus);
	}

	public static UserDetailsResponseDTO detailsToDto(UserEntity entity) {
	    if (entity == null)
	        return null;

	    Set<RoleDetailsResponseDTO> roles = entity.getUserRoles().stream()
	            .map(UserRoleEntity::getRole)
	            .map(RolesMapper::detailToDto)
	            .collect(Collectors.toSet());

	    Set<PrivilegeResponseDTO> privileges = entity.getUserPrivilegies().stream()
	            .map(PrivilegeUserEntity::getPrivilege)
	            .map(PrivilegeMapper::toDto)
	            .collect(Collectors.toSet());

	    // Menús del usuario
	    Set<MenuEntity> userMenus = entity.getUserMenus().stream()
	            .map(MenuUserEntity::getMenu)
	            .collect(Collectors.toSet());

	    // Menús de los roles
	    Set<MenuEntity> roleMenus = entity.getUserRoles().stream()
	            .flatMap(role -> role.getRole().getRoleMenus().stream())
	            .map(MenuRoleEntity::getMenu)
	            .collect(Collectors.toSet());

	    // Combinar y mapear
	    Set<MenuEntity> combinedMenus = new HashSet<>();
	    combinedMenus.addAll(userMenus);
	    combinedMenus.addAll(roleMenus);

	    Set<MenuResponseDTO> flatMenuDtos = combinedMenus.stream()
	            .map(MenuMapper::toDto)
	            .collect(Collectors.toSet());

	    // Convertir a estructura jerárquica si tu frontend espera children
	    Set<MenuResponseDTO> treeMenus = MenuMapper.buildMenuTree(flatMenuDtos);

	    return new UserDetailsResponseDTO(
	            entity.getId(),
	            entity.getUsername(),
	            entity.getEmail(),
	            entity.getFirstNames(),
	            entity.getEmployeeId(),
	            entity.getLastModificationDate(),
	            entity.getLastConnection(),
	            entity.isLoggedIn(),
	            entity.isActive(),
	            entity.isSuspended(),
	            roles,
	            privileges,
	            treeMenus
	    );
	}


	public static UserLoginResponseDTO detailsLoginToDto(UserEntity entity) {
		if (entity == null)
			return null;

		Set<RolesResponseDTO> roles = entity.getUserRoles().stream().map(UserRoleEntity::getRole)
				.map(RolesMapper::toDto).collect(Collectors.toSet());

		Set<PrivilegeResponseDTO> privileges = entity.getUserPrivilegies().stream()
				.map(PrivilegeUserEntity::getPrivilege).map(PrivilegeMapper::toDto).collect(Collectors.toSet());

		// Menús del usuario
		Set<MenuEntity> userMenus = entity.getUserMenus().stream().map(MenuUserEntity::getMenu)
				.collect(Collectors.toSet());

		// Menús de los roles
		Set<MenuEntity> roleMenus = entity.getUserRoles().stream()
				.flatMap(role -> role.getRole().getRoleMenus().stream()).map(MenuRoleEntity::getMenu)
				.collect(Collectors.toSet());

		Set<MenuEntity> combinedMenus = new HashSet<>();
		combinedMenus.addAll(userMenus);
		combinedMenus.addAll(roleMenus);
		Set<MenuResponseDTO> flatMenuDtos = combinedMenus.stream().map(MenuMapper::toDto).collect(Collectors.toSet());

		Set<MenuResponseDTO> treeMenus = MenuMapper.buildMenuTree(flatMenuDtos);

		return new UserLoginResponseDTO(entity.getUsername(), entity.getEmail(), entity.getFirstNames(), null, roles,
				privileges, treeMenus);
	}

}
