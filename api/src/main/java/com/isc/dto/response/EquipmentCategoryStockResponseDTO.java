package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryStockResponseDTO {
	private Integer id;
	private Integer category;
	private Integer stock;
	
}
