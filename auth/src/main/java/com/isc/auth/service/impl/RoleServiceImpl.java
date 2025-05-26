package com.isc.auth.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.auth.dto.request.RoleRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.dto.response.UserRegisterResponseDTO;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;
import com.isc.auth.mapper.PrivilegeMapper;
import com.isc.auth.mapper.RolesMapper;
import com.isc.auth.mapper.UserRegisterMapper;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.repository.RolesRepository;
import com.isc.auth.service.AuthenticatedUserService;
import com.isc.auth.service.RoleService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RolesRepository rolesRepository;
	private final PrivilegesRepository privilegiesRepository;

	@Override
	public ResponseDto<List<RolesResponseDTO>> getAll() {
		List<RolesResponseDTO> privileges = rolesRepository.findAllByActiveTrue().stream().map(RolesMapper::toDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Roles listados correctamente");

		return new ResponseDto<>(privileges, metadata);
	}

	@Override
	public ResponseDto<RolesResponseDTO> save(RoleRequestDTO request) {
		Set<PrivilegeEntity> privileges = privilegiesRepository.findByIdIn(request.getPrivilegesId());
		RolesEntity rol = new RolesEntity();
		rol.setName(request.getName());
		rol.setDescription(request.getDescription());
		rol.setApplicationId(request.getApplicationId());
		Set<PrivilegeRoleEntity> privilegeRoles = new HashSet<>();
		RolesEntity savedRole = rolesRepository.save(rol);
		for (PrivilegeEntity privilege : privileges) {
			PrivilegeRoleEntity privilegeRole = new PrivilegeRoleEntity();
			privilegeRole.setRoleId(savedRole.getId());
			privilegeRole.setPrivilege(privilege);
			privilegeRoles.add(privilegeRole);
		}
		savedRole.getRolesPrivilegies().clear();
		savedRole.getRolesPrivilegies().addAll(privilegeRoles);
		savedRole = rolesRepository.save(savedRole);

		RolesResponseDTO responseDTO = RolesMapper.toDto(savedRole);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Rol registrado correctamente");

		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<RolesResponseDTO> addPrivileges(RoleRequestDTO request, Integer id) {
		Set<PrivilegeEntity> privileges = privilegiesRepository.findByIdIn(request.getPrivilegesId());
		RolesEntity rol = rolesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
		Set<PrivilegeRoleEntity> privilegeRoles = new HashSet<>();
		for (PrivilegeEntity privilege : privileges) {
			PrivilegeRoleEntity privilegeRole = new PrivilegeRoleEntity();
			privilegeRole.setRoleId(rol.getId());
			privilegeRole.setPrivilege(privilege);
			privilegeRoles.add(privilegeRole);
		}
		rol.getRolesPrivilegies().clear();
		rol.getRolesPrivilegies().addAll(privilegeRoles);
		rol = rolesRepository.save(rol);
		RolesResponseDTO responseDTO = RolesMapper.toDto(rol);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Rol actualizado correctamente");

		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactiveRole(Integer id) {
		int rowsAffected = rolesRepository.inactive(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> activeRole(Integer id) {
		int rowsAffected = rolesRepository.active(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
