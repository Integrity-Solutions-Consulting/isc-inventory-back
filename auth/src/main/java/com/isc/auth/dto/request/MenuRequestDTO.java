package com.isc.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDTO {
	private String label;
	private String description;
	private String route;
	private Integer parentId;
	private Boolean active;
	private String icon;
	private Integer order;
	private Boolean internalRoute;
	private Integer applicationId;
}
