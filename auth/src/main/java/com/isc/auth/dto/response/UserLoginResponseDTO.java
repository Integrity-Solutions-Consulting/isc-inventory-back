package com.isc.auth.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {
	private String username;
	private String email;
	private String firstNames;
	private String token;
	private Set<RolesResponseDTO> roles;
	private Set<PrivilegeResponseDTO> privileges;
	private Set<MenuResponseDTO> menus;
}
