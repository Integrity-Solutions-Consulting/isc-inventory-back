package com.isc.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRepairResponseDTO {
	private Integer id;
	private Integer equipment;
	private LocalDateTime repairDate;
	private String description;
	private BigDecimal cost;
	private String serviceProvider;
	
}
