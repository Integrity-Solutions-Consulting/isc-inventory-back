package com.isc.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCharacteristicsReportResponseDTO {
	private String component;
	private String characteristic;
}
