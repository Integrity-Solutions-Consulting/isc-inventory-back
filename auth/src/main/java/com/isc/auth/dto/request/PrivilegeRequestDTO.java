package com.isc.auth.dto.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeRequestDTO {
	private String key;
	private String description;
	private Integer applicationId;
}
