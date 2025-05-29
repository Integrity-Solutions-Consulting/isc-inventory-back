package com.isc.auth.dto.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
	private String name;
	private String description;
	private Integer applicationId;
	private Set<Integer> privilegesId;
	private Set<Integer> menusId;
}
