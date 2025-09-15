package com.isc.auth.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeResponseDTO {
	private Integer id;
	private String key;
	private boolean active;
	private Integer applicationId;
	private LocalDateTime creationDate;
	private LocalDateTime lastModificationDate;
}
