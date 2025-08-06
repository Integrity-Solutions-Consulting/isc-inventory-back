package com.isc.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRepairDetailResponseDTO {
	private Integer id;
	private Integer equipment;
	private String serialNumber;
	private EquipmentStatusResponseDTO equipmentStatus;
	private LocalDate repairDate;
	private String description;
	private BigDecimal cost;
	private String serviceProvider;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private EquipmentStatusResponseDTO repairStatus;
}
