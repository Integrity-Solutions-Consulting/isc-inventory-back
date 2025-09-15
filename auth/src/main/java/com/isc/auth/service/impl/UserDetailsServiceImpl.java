package com.isc.auth.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.entitys.PrivilegeRoleEntity;
import com.isc.auth.entitys.PrivilegeUserEntity;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.RolesEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.entitys.UserRoleEntity;
import com.isc.auth.repository.UserRoleRepository;
import com.isc.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository repository;
	private final UserRoleRepository usuarioRolRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Obtener el usuario
		UserEntity user = repository.findByEmailAndActiveTrueAndSuspendedFalse(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " no existe"));

		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getUserRoles().stream().filter(UserRoleEntity::isActive).map(ur -> "ROLE_" + ur.getRole().getName())
				.map(SimpleGrantedAuthority::new).forEach(authorities::add);
		user.getUserRoles().stream().filter(UserRoleEntity::isActive).map(UserRoleEntity::getRole)
				.filter(RolesEntity::isActive).flatMap(role -> role.getRolesPrivilegies().stream())
				.filter(PrivilegeRoleEntity::getActive).map(PrivilegeRoleEntity::getPrivilege)
				.map(PrivilegeEntity::getKey).map(SimpleGrantedAuthority::new).forEach(authorities::add);

		user.getUserPrivilegies().stream().filter(PrivilegeUserEntity::getActive).map(PrivilegeUserEntity::getPrivilege)
				.map(PrivilegeEntity::getKey).map(SimpleGrantedAuthority::new).forEach(authorities::add);

		return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);

	}
}
