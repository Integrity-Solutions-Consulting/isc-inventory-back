package com.isc.auth.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDetailsResponseDTO {
	private Integer id;
	private String nombre;
	private boolean active;
	private Set<PrivilegeResponseDTO> rolePrivileges;
	private Set<MenuResponseDTO> roleMenus;
}
