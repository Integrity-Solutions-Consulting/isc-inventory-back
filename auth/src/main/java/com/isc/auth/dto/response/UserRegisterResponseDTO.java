package com.isc.auth.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponseDTO {
	private Integer id;
	private String username;
	private String email;
	private String firstNames;
	private Integer employeeId;
	private boolean isLoggedIn;
	private boolean active;
	private boolean suspended;
	private Set<RolesResponseDTO> roles;
	private Set<PrivilegeResponseDTO> privileges;
	private String password;
}
