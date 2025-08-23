package com.isc.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RoleDetailsResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.dto.response.UserDetailsResponseDTO;
import com.isc.auth.dto.response.UserLoginResponseDTO;
import com.isc.auth.dto.response.UserResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.entitys.MenuRoleEntity;
import com.isc.auth.entitys.MenuUserEntity;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;
import com.isc.auth.mapper.RolesMapper;
import com.isc.auth.mapper.UserMapper;
import com.isc.auth.repository.MenuRepository;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.repository.RolesRepository;
import com.isc.auth.repository.UserRepository;
import com.isc.auth.service.AuthenticatedUserService;
import com.isc.auth.service.UserService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RolesRepository rolesRepository;
	private final PrivilegesRepository privilegesRepository;
	private final MenuRepository menuRepository;
	private final PasswordEncoder encoder;

	private final AuthenticatedUserService authenticatedUserService;

	@Override
	public ResponseDto<List<UserResponseDTO>> getAll() {
		List<UserEntity> u = userRepository.findAll();
		System.out.println(u);
		List<UserResponseDTO> usuarios = userRepository.findAll().stream().map(UserMapper::toDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Usuarios listados correctamente");

		return new ResponseDto<>(usuarios, metadata);
	}

	@Override
	public ResponseDto<UserDetailsResponseDTO> getDetailsById(Integer id) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
		UserDetailsResponseDTO responseDTO = UserMapper.detailsToDto(user);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Detalles cargados correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	@Transactional
	public ResponseDto<UserResponseDTO> updateUser(UserRequestoDTO request, Integer id) {
	    UserEntity userConnected = authenticatedUserService.getAuthenticatedUser();
	    if (!authenticatedUserService.isSelfOrAdmin(userConnected, id)) {
	        throw new RuntimeException("No tienes permiso para modificar este usuario");
	    }

	    UserEntity user = authenticatedUserService.isAdmin(userConnected)
	        ? userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id))
	        : userConnected;
	    System.out.println(user);

	    if (authenticatedUserService.isAdmin(userConnected)) {
	        // Obtener nuevos datos
	        Set<RolesEntity> roles = rolesRepository.findByIdIn(request.getRolesId());
	        Set<PrivilegeEntity> privileges = privilegesRepository.findByIdIn(request.getPrivilegesId());
	        Set<MenuEntity> menus = menuRepository.findByIdIn(request.getMenusId());

	        if (roles.isEmpty()) {
	            throw new RuntimeException("Roles not found");
	        }

	        // === ROLES ===
	        Set<UserRoleEntity> currentRoles = user.getUserRoles();
	        Set<Integer> requestedRoleIds = roles.stream().map(RolesEntity::getId).collect(Collectors.toSet());

	        // Desactivar roles no solicitados
	        for (UserRoleEntity ur : currentRoles) {
	            if (!requestedRoleIds.contains(ur.getRole().getId()) && ur.isActive()) {
	                ur.setActive(false);
	            }
	        }

	        // Activar roles existentes inactivos o agregar nuevos
	        Set<Integer> currentRoleIds = currentRoles.stream()
	            .map(ur -> ur.getRole().getId())
	            .collect(Collectors.toSet());

	        for (RolesEntity role : roles) {
	            Optional<UserRoleEntity> existing = currentRoles.stream()
	                .filter(ur -> ur.getRole().getId().equals(role.getId()))
	                .findFirst();

	            if (existing.isPresent()) {
	                UserRoleEntity ur = existing.get();
	                if (!ur.isActive()) {
	                    ur.setActive(true);
	                }
	            } else {
	                UserRoleEntity userRole = new UserRoleEntity();
	                userRole.setRole(role);
	                userRole.setUserId(user.getId());
	                userRole.setActive(true);
	                currentRoles.add(userRole);
	            }
	        }

	        // === PRIVILEGIOS ===
	        Set<PrivilegeRoleEntity> rolePrivileges = roles.stream()
	            .flatMap(r -> r.getRolesPrivilegies().stream())
	            .collect(Collectors.toSet());

	        Set<PrivilegeEntity> privilegesFromRoles = rolePrivileges.stream()
	            .map(PrivilegeRoleEntity::getPrivilege)
	            .collect(Collectors.toSet());

	        Set<PrivilegeUserEntity> currentPrivileges = user.getUserPrivilegies();
	        Set<Integer> requestedPrivilegeIds = privileges.stream().map(PrivilegeEntity::getId).collect(Collectors.toSet());

	        // Desactivar privilegios no solicitados
	        for (PrivilegeUserEntity pu : currentPrivileges) {
	            if (!requestedPrivilegeIds.contains(pu.getPrivilege().getId()) && pu.getActive()) {
	                pu.setActive(false);
	            }
	        }

	        // Activar privilegios existentes inactivos o agregar nuevos que no vienen por rol
	        Set<Integer> currentPrivilegeIds = currentPrivileges.stream()
	            .map(pu -> pu.getPrivilege().getId())
	            .collect(Collectors.toSet());

	        for (PrivilegeEntity p : privileges) {
	            Optional<PrivilegeUserEntity> existing = currentPrivileges.stream()
	                .filter(pu -> pu.getPrivilege().getId().equals(p.getId()))
	                .findFirst();

	            if (existing.isPresent()) {
	                PrivilegeUserEntity pu = existing.get();
	                if (!pu.getActive()) {
	                    pu.setActive(true);
	                }
	            } else {
	                if (!privilegesFromRoles.contains(p)) {
	                    PrivilegeUserEntity newPU = new PrivilegeUserEntity();
	                    newPU.setUserId(user.getId());
	                    newPU.setPrivilege(p);
	                    newPU.setActive(true);
	                    currentPrivileges.add(newPU);
	                }
	            }
	        }

	        // === MENÚS ===
	        Set<MenuRoleEntity> roleMenus = roles.stream()
	            .flatMap(r -> r.getRoleMenus().stream())
	            .collect(Collectors.toSet());

	        Set<MenuEntity> menusFromRoles = roleMenus.stream()
	            .map(MenuRoleEntity::getMenu)
	            .collect(Collectors.toSet());

	        Set<MenuUserEntity> currentMenus = user.getUserMenus();
	        Set<Integer> requestedMenuIds = menus.stream().map(MenuEntity::getId).collect(Collectors.toSet());

	        // Desactivar menús no solicitados (que no vienen por rol)
	        for (MenuUserEntity mu : currentMenus) {
	            if (!requestedMenuIds.contains(mu.getMenu().getId()) && mu.getActive() && !menusFromRoles.contains(mu.getMenu())) {
	                mu.setActive(false);
	            }
	        }

	        // Activar menús existentes inactivos o agregar nuevos que no están en roles
	        Set<Integer> currentMenuIds = currentMenus.stream()
	            .map(mu -> mu.getMenu().getId())
	            .collect(Collectors.toSet());

	        for (MenuEntity menu : menus) {
	            Optional<MenuUserEntity> existing = currentMenus.stream()
	                .filter(mu -> mu.getMenu().getId().equals(menu.getId()))
	                .findFirst();

	            if (existing.isPresent()) {
	                MenuUserEntity mu = existing.get();
	                if (!mu.getActive()) {
	                    mu.setActive(true);
	                }
	            } else if (!menusFromRoles.contains(menu)) {
	                MenuUserEntity menuUser = new MenuUserEntity();
	                menuUser.setUserId(user.getId());
	                menuUser.setMenu(menu);
	                menuUser.setActive(true);
	                currentMenus.add(menuUser);
	            }
	        }

	        // Employee
	        user.setEmployeeId(request.getEmployeeId());
	    }

	    // Datos comunes
	    user.setFirstNames(request.getFirstNames());
	    user.setEmail(request.getEmail());
	    user.setUsername(request.getUsername());
	    user.setLastModificationDate(LocalDateTime.now());

	    UserEntity updatedUser = userRepository.save(user);
	    UserResponseDTO responseDTO = UserMapper.toDto(updatedUser);
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Usuario actualizado correctamente");

	    return new ResponseDto<>(responseDTO, metadata);
	}


	@Override
	public ResponseDto<MessageResponseDTO> changePassword(PasswordChangeRequestDTO request, Integer id) {
		UserEntity userConnected = authenticatedUserService.getAuthenticatedUser();
		UserEntity userToChange = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		/*if (!encoder.matches(request.getActualPassword(), userConnected.getPassword())) {
		    throw new RuntimeException("La contraseña actual es incorrecta");
		}*/
		if (!authenticatedUserService.isSelfOrAdmin(userConnected, id)) {
			throw new RuntimeException("No tienes permiso para modificar este usuario");
		}
		if (!encoder.matches(request.getActualPassword(), userConnected.getPassword())) {
			throw new RuntimeException("La contraseña actual es incorrecta");
		}
		if (!request.getNewPassword().equals(request.getConfirmPassword())) {
			throw new RuntimeException("La nueva contraseña y su confirmación no coinciden");
		}
		userToChange.setPassword(encoder.encode(request.getNewPassword()));
		userToChange.setLastPasswordChangeDate(LocalDateTime.now());
		userRepository.save(userToChange);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Contraseña actualizada correctamente");
		MessageResponseDTO message = new MessageResponseDTO("Actualizacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> deleteUser(Integer id) {
		UserEntity userConnected = authenticatedUserService.getAuthenticatedUser();
		if(authenticatedUserService.isSelfOrAdmin(userConnected, id)) {
			throw new RuntimeException("No tiene permisos para realizar esta acción");
		}
		int rowsAffected = userRepository.softDelete(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> activeUser(Integer id) {
		int rowsAffected = userRepository.activateUser(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> suspendUser(Integer id) {
		int rowsAffected = userRepository.suspendUser(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> unsuspendUser(Integer id) {
		int rowsAffected = userRepository.unsuspendUser(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<List<UserResponseDTO>> getAllUserDTOs() {
	    List<UserEntity> users = userRepository.findAllWithRolesPrivilegesAndMenus();

	    List<UserResponseDTO> usersMaped = users.stream().map(UserMapper::toDto)
				.collect(Collectors.toList());

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		return new ResponseDto<>(usersMaped, metadata);
	    
	}
	
	@Override
	@Transactional
	public UserLoginResponseDTO processLogin(String email, Collection<? extends GrantedAuthority> authorities) {
		UserEntity userEntity = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		userEntity.setLoggedIn(true);
		userEntity.setLastConnection(LocalDateTime.now());

		userRepository.save(userEntity);

		return UserMapper.detailsLoginToDto(userEntity);
	}

}
