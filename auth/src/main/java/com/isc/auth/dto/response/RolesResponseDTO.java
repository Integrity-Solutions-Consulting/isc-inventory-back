package com.isc.auth.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponseDTO {
	private Integer id;
	private String name;
	private String description;
	private boolean active;
	private Set<PrivilegeResponseDTO> rolePrivileges;
	private Set<MenuResponseDTO> menus;
	private Integer applicationId;
	private LocalDateTime creationDate;
}
