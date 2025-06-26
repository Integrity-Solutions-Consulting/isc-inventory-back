package com.isc.auth.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private Integer id;
	private String username;
	private String email;
	private String firstNames;
	private Integer employeeId;
	private LocalDateTime lastModificationDate;
	private LocalDateTime lastConnection;
	private boolean isLoggedIn;
	private boolean active;
	private boolean suspended;
	private Set<RolesResponseDTO> roles;
	private Set<PrivilegeResponseDTO> privileges;
	private Set<MenuResponseDTO> menus;
}
