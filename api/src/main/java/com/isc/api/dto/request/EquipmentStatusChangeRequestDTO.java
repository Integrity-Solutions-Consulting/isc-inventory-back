package com.isc.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentStatusChangeRequestDTO {
	private Integer status;
	private boolean revoke;
}
