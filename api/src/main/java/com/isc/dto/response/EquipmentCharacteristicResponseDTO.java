package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCharacteristicResponseDTO {
	private Integer id;
	private String description;
	private Integer component;
	
}
