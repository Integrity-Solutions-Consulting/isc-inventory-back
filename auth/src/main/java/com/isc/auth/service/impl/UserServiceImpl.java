package com.isc.auth.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.UserResponseDTO;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;
import com.isc.auth.mapper.UserMapper;
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
	private final PasswordEncoder encoder;

	private final AuthenticatedUserService authenticatedUserService;

	@Override
	public ResponseDto<List<UserResponseDTO>> getAll() {
		List<UserResponseDTO> usuarios = userRepository.findAll().stream().map(UserMapper::toDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Usuarios listados correctamente");

		return new ResponseDto<>(usuarios, metadata);
	}

	@Override
	public ResponseDto<UserResponseDTO> updateUser(UserRequestoDTO request, Integer id) {
		UserEntity userConnected = authenticatedUserService.getAuthenticatedUser();
		if (!authenticatedUserService.isSelfOrAdmin(userConnected, id)) {
			throw new RuntimeException("No tienes permiso para modificar este usuario");
		}
		UserEntity user = new UserEntity();
		if (authenticatedUserService.isAdmin(userConnected)) {
			user = userRepository.findById(id)
			        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

			// Obtener nuevos roles y privilegios desde la request
			Set<RolesEntity> roles = rolesRepository.findByIdIn(request.getRolesId());
			Set<PrivilegeEntity> privileges = privilegesRepository.findByIdIn(request.getPrivilegesId());

			// Limpiar roles y privilegios anteriores
			if (user.getUserRoles() != null) {
			    user.getUserRoles().clear();
			}
			if (user.getUserPrivilegies() != null) {
			    user.getUserPrivilegies().clear();
			}

			// Asignar nuevos roles
			Set<UserRoleEntity> usuarioRoles = new HashSet<>();
			Set<PrivilegeRoleEntity> rolePrivileges = new HashSet<>();

			for (RolesEntity rol : roles) {
			    UserRoleEntity usuarioRole = new UserRoleEntity();
			    usuarioRole.setRole(rol);
			    usuarioRole.setUserId(user.getId());
			    usuarioRoles.add(usuarioRole);
			    
			    // Recolectar privilegios de los roles
			    rolePrivileges.addAll(rol.getRolesPrivilegies());
			}

			// Extraer privilegios de roles para evitar duplicados
			Set<PrivilegeEntity> privilegesFromRoles = rolePrivileges.stream()
			        .map(PrivilegeRoleEntity::getPrivilege)
			        .collect(Collectors.toSet());

			// Asignar privilegios que no están ya en los roles
			Set<PrivilegeUserEntity> userPrivileges = new HashSet<>();
			for (PrivilegeEntity privilege : privileges) {
			    if (!privilegesFromRoles.contains(privilege)) {
			        PrivilegeUserEntity privilegeUser = new PrivilegeUserEntity();
			        privilegeUser.setUserId(user.getId());
			        privilegeUser.setPrivilege(privilege);
			        userPrivileges.add(privilegeUser);
			    }
			}

			// Asignar todo al usuario
			user.setUserRoles(usuarioRoles);
			user.setUserPrivilegies(userPrivileges);
			user.setEmployeeId(request.getEmployeeId());
		} else {
			user = userConnected;
		}
		user.setFirstNames(request.getFirstNames());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setLastConnection(LocalDateTime.now());
		UserEntity updatedUser = userRepository.save(user);
		UserResponseDTO responseDTO = UserMapper.toDto(updatedUser);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Usuario actualizado correctamente");

		return new ResponseDto<>(responseDTO, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> changePassword(PasswordChangeRequestDTO request, Integer id){
		UserEntity userConnected = authenticatedUserService.getAuthenticatedUser();
		if (!authenticatedUserService.isSelfOrAdmin(userConnected, id)) {
			throw new RuntimeException("No tienes permiso para modificar este usuario");
		}
	    if (!encoder.matches(request.getActualPassword(), userConnected.getPassword())) {
	        throw new RuntimeException("La contraseña actual es incorrecta");
	    }
	    if (!request.getNewPassword().equals(request.getConfirmPassword())) {
	        throw new RuntimeException("La nueva contraseña y su confirmación no coinciden");
	    }
	    userConnected.setPassword(encoder.encode(request.getNewPassword()));
	    userConnected.setLastPasswordChangeDate(LocalDateTime.now());
	    userRepository.save(userConnected);
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Contraseña actualizada correctamente");
		MessageResponseDTO message = new MessageResponseDTO("Actualizacion exitosa");
	    return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> deleteUser(Integer id) {
		int rowsAffected =  userRepository.softDelete(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> activeUser(Integer id) {
		int rowsAffected =  userRepository.activateUser(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> suspendUser(Integer id) {
		int rowsAffected =  userRepository.suspendUser(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> unsuspendUser(Integer id) {
		int rowsAffected =  userRepository.unsuspendUser(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	
}
