package com.isc.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCharacteristicDetailResponseDTO {
	private Integer id;
	private String description;
	private Integer component;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
}
