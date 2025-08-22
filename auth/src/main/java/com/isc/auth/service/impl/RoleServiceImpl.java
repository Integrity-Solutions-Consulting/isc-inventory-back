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
import com.isc.auth.dto.response.RoleDetailsResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.entitys.MenuRoleEntity;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.mapper.RolesMapper;
import com.isc.auth.repository.MenuRepository;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.repository.RolesRepository;
import com.isc.auth.service.RoleService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RolesRepository rolesRepository;
	private final PrivilegesRepository privilegiesRepository;
	private final MenuRepository menuRepository;

	@Override
	public ResponseDto<List<RolesResponseDTO>> getAll() {
		List<RolesResponseDTO> privileges = rolesRepository.findAllByActiveTrue().stream().map(RolesMapper::toDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Roles listados correctamente");

		return new ResponseDto<>(privileges, metadata);
	}

	@Override
	public ResponseDto<List<RoleDetailsResponseDTO>> getTable() {
		List<RoleDetailsResponseDTO> privileges = rolesRepository.findAllByActiveTrue().stream()
				.map(RolesMapper::detailToDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Roles listados correctamente");

		return new ResponseDto<>(privileges, metadata);
	}

	@Override
	public ResponseDto<RoleDetailsResponseDTO> getDetailsById(Integer id) {
		RolesEntity rol = rolesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
		RoleDetailsResponseDTO responseDTO = RolesMapper.detailToDto(rol);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Detalles cargados correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<RolesResponseDTO> save(RoleRequestDTO request) {
		Set<PrivilegeEntity> privileges = privilegiesRepository.findByIdIn(request.getPrivilegesId());
		Set<MenuEntity> menus = menuRepository.findByIdIn(request.getMenusId());
		RolesEntity rol = new RolesEntity();
		rol.setName(request.getName());
		rol.setDescription(request.getDescription());
		rol.setApplicationId(3);//quemado por el momento para pruebas - cambiar por base de datos
		RolesEntity savedRole = rolesRepository.save(rol);
		Set<PrivilegeRoleEntity> privilegeRoles = new HashSet<>();

		for (PrivilegeEntity privilege : privileges) {
			PrivilegeRoleEntity privilegeRole = new PrivilegeRoleEntity();
			privilegeRole.setRoleId(savedRole.getId());
			privilegeRole.setPrivilege(privilege);
			privilegeRoles.add(privilegeRole);
		}
		Set<MenuRoleEntity> menusRole = new HashSet<>();
		for (MenuEntity menu : menus) {
			MenuRoleEntity menuRole = new MenuRoleEntity();
			menuRole.setRoleId(savedRole.getId());
			menuRole.setMenu(menu);
			menusRole.add(menuRole);
		}

		savedRole.getRolesPrivilegies().clear();
		savedRole.getRolesPrivilegies().addAll(privilegeRoles);
		savedRole.getRoleMenus().clear();
		savedRole.getRoleMenus().addAll(menusRole);
		savedRole = rolesRepository.save(savedRole);

		RolesResponseDTO responseDTO = RolesMapper.toDto(savedRole);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Rol registrado correctamente");

		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<RolesResponseDTO> update(RoleRequestDTO request, Integer id) {
		RolesEntity existingRole = rolesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		// Obtener los nuevos privilegios y menús del request
		Set<Integer> requestedPrivilegeIds = new HashSet<>(request.getPrivilegesId());
		Set<Integer> requestedMenuIds = new HashSet<>(request.getMenusId());

		// Actualizar Privilegios
		for (PrivilegeRoleEntity pr : existingRole.getRolesPrivilegies()) {
			boolean shouldBeActive = requestedPrivilegeIds.contains(pr.getPrivilege().getId());
			pr.setActive(shouldBeActive); // marcar activo o inactivo
			requestedPrivilegeIds.remove(pr.getPrivilege().getId()); // eliminar los ya existentes
		}

		// Agregar nuevos privilegios que no existían antes
		if (!requestedPrivilegeIds.isEmpty()) {
			Set<PrivilegeEntity> newPrivileges = privilegiesRepository.findByIdIn(requestedPrivilegeIds);
			for (PrivilegeEntity privilege : newPrivileges) {
				PrivilegeRoleEntity newPR = new PrivilegeRoleEntity();
				newPR.setRoleId(existingRole.getId());
				newPR.setPrivilege(privilege);
				newPR.setActive(true);
				existingRole.getRolesPrivilegies().add(newPR);
			}
		}

		// Actualizar Menús
		for (MenuRoleEntity mr : existingRole.getRoleMenus()) {
			boolean shouldBeActive = requestedMenuIds.contains(mr.getMenu().getId());
			mr.setActive(shouldBeActive);
			requestedMenuIds.remove(mr.getMenu().getId());
		}

		// Agregar nuevos menús que no existían antes
		if (!requestedMenuIds.isEmpty()) {
			Set<MenuEntity> newMenus = menuRepository.findByIdIn(requestedMenuIds);
			for (MenuEntity menu : newMenus) {
				MenuRoleEntity newMR = new MenuRoleEntity();
				newMR.setRoleId(existingRole.getId());
				newMR.setMenu(menu);
				newMR.setActive(true);
				existingRole.getRoleMenus().add(newMR);
			}
		}

		// Guardar cambios
		RolesEntity updatedRole = rolesRepository.save(existingRole);
		RolesResponseDTO responseDTO = RolesMapper.toDto(updatedRole);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Rol actualizado correctamente");

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
	public ResponseDto<RolesResponseDTO> addMenus(RoleRequestDTO request, Integer id) {
		Set<MenuEntity> menus = menuRepository.findByIdIn(request.getMenusId());
		RolesEntity rol = rolesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
		Set<MenuRoleEntity> menusRole = new HashSet<>();
		for (MenuEntity menu : menus) {
			MenuRoleEntity menuRole = new MenuRoleEntity();
			menuRole.setRoleId(rol.getId());
			menuRole.setMenu(menu);
			menusRole.add(menuRole);
		}

		rol.getRoleMenus().clear();
		rol.getRoleMenus().addAll(menusRole);
		RolesEntity rolUpdate = rolesRepository.save(rol);
		RolesResponseDTO responseDTO = RolesMapper.toDto(rolUpdate);
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
