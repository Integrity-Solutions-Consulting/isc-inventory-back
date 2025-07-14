package com.isc.auth.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.TokenResponseDTO;
import com.isc.auth.dto.response.UserRegisterResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.entitys.MenuRoleEntity;
import com.isc.auth.entitys.MenuUserEntity;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;
import com.isc.auth.mapper.UserRegisterMapper;
import com.isc.auth.repository.MenuRepository;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.repository.RolesRepository;
import com.isc.auth.repository.UserRepository;
import com.isc.auth.security.JwtService;
import com.isc.auth.service.AuthService;
import com.isc.auth.service.EmailService;
import com.isc.auth.utils.PasswordGenerator;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final RolesRepository rolesRepository;
	private final PrivilegesRepository privilegesRepository;
	private final MenuRepository menuRepository;
	private final PasswordEncoder encoder;
	private final JwtService jwtService;

	private final EmailService emailService;
	@Override
	@Transactional
	public ResponseDto<UserRegisterResponseDTO> register(UserRequestoDTO request) {
		// TODO Auto-generated method stub
		Set<RolesEntity> roles = rolesRepository.findByIdIn(request.getRolesId());
		if(roles.size()==0) {
			throw new RuntimeException("Roles not found");
		}
		Set<PrivilegeEntity> privileges = privilegesRepository.findByIdIn(request.getPrivilegesId());
		Set<MenuEntity> menus = menuRepository.findByIdIn(request.getMenusId());
		
		UserEntity user = new UserEntity();
		String password = PasswordGenerator.generatePassword();
		user.setFirstNames(request.getFirstNames());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(encoder.encode(password));
		user.setEmployeeId(request.getEmployeeId());
		UserEntity savedUser = userRepository.save(user);

		Set<UserRoleEntity> usuarioRoles = new HashSet<>();
		Set<PrivilegeRoleEntity> rolePrivileges = new HashSet<>();
		Set<MenuRoleEntity> roleMenus = new HashSet<>();
		for (RolesEntity rol : roles) {
			UserRoleEntity usuarioRole = new UserRoleEntity();
			usuarioRole.setRole(rol);
			usuarioRole.setUserId(savedUser.getId());
			usuarioRoles.add(usuarioRole);
			rolePrivileges.addAll(rol.getRolesPrivilegies());
			roleMenus.addAll(rol.getRoleMenus());
		}

		Set<PrivilegeEntity> privilegesFromRoles = rolePrivileges.stream().map(PrivilegeRoleEntity::getPrivilege)
				.collect(Collectors.toSet());
		Set<PrivilegeUserEntity> userPrivileges = new HashSet<>();
		for (PrivilegeEntity privilege : privileges) {
			if (!privilegesFromRoles.contains(privilege)) {
		        PrivilegeUserEntity privilegeUser = new PrivilegeUserEntity();
		        privilegeUser.setUserId(savedUser.getId());
		        privilegeUser.setPrivilege(privilege);
		        userPrivileges.add(privilegeUser);
		    }
		}
		
		Set<MenuEntity> menusFromRoles = roleMenus.stream().map(MenuRoleEntity::getMenu)
				.collect(Collectors.toSet());
		Set<MenuUserEntity> userMenus = new HashSet<>();
		for (MenuEntity menu : menus) {
			if (!menusFromRoles.contains(menu)) {
		        MenuUserEntity menuUser = new MenuUserEntity();
		        menuUser.setUserId(savedUser.getId());
		        menuUser.setMenu(menu);
		        userMenus.add(menuUser);
		    }
		}

		savedUser.getUserRoles().clear();
		savedUser.getUserRoles().addAll(usuarioRoles);
		savedUser.getUserPrivilegies().clear();
		savedUser.getUserPrivilegies().addAll(userPrivileges);
		savedUser.getUserMenus().clear();
		savedUser.getUserMenus().addAll(userMenus);
		savedUser = userRepository.save(savedUser);
		
		emailService.sendUserCreatedEmail(user.getEmail(), password);
		

		

		UserRegisterResponseDTO responseDTO = UserRegisterMapper.toDto(savedUser, password);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Usuario registrado correctamente");

		return new ResponseDto<>(responseDTO, metadata);
	}

	@Transactional
	public ResponseDto<MessageResponseDTO> generateTokenForgotPassword(String email) {
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("email no encontrado"));
		user.setRequestPasswordChange(true);
		userRepository.save(user);
		emailService.sendForgotPasswordEmail(email, jwtService.generatePasswordResetToken(email));
		MessageResponseDTO message = new MessageResponseDTO("Si el correo existe , recibiras un enlace para recuoerrar tu cuenta");
		
		
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,
				"Solicitud de recuperacion de contraseña procesada correctamente");
		return new ResponseDto<>(message, metadata);
		
		
	}

	
	public ResponseDto<MessageResponseDTO> validateTokenForgotPassword(String token) {
		if (jwtService.isTokenValid(token) && jwtService.isPasswordResetToken(token)) {
			String email = jwtService.extractUsername(token);
			MessageResponseDTO message = new MessageResponseDTO(email);
			MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,
					"Token valido para el email " + email);
			return new ResponseDto<>(message, metadata);
		}
		throw new IllegalArgumentException("Token inválido o expirado");
	}

	@Transactional
	public ResponseDto<Boolean> restorePassword(String token, PasswordChangeRequestDTO request) {
		if (jwtService.isTokenValid(token) && jwtService.isPasswordResetToken(token)) {
			String email = jwtService.extractUsername(token);
			UserEntity user = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Email no encontrado"));
			if (!request.getNewPassword().equals(request.getConfirmPassword())) {
				throw new RuntimeException("Contraseñas no coinciden");
			}
			user.setPassword(encoder.encode(request.getNewPassword()));
			user.setLastPasswordChangeDate(LocalDateTime.now());
			userRepository.save(user);
			MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,
					"Actualizacion de contraseña exitosa para el email " + email);
			return new ResponseDto<>(true, metadata);
		}
		throw new IllegalArgumentException("Token inválido o expirado");
	}

	/*
	 * { "username": "employee1", "password": "p5bkDlO&&YIj" } "username":
	 * "employee2", "password": "XWjVd2lq)t8U",
	 * 
	 * "username": "employee3", "password": "=BV8+frPmH^!",
	 */
}
