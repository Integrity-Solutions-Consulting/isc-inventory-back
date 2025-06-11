package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryStockDetailResponseDTO {
	private Integer id;
	private Integer category;
	private Integer stock;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
