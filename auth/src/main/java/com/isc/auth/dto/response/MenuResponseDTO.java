package com.isc.auth.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDTO {
	private Integer id;
	private String label;
	private String description;
	private String route;
	private Integer parentId;
	private Boolean active;
	private String icon;
	private Integer order;
	private LocalDateTime creationDate;
	private LocalDateTime lastModificationDate;
	private Boolean internalRoute;
	private Integer applicationId;
	private List<MenuResponseDTO> children;
}
