package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentResponseDTO {
	private Integer id;
	private Integer invoice;
	private Integer equipStatus;
	private Integer category;
	private Integer company;
	private Integer characteristic;
	private String brand;
	private String model;
	private String serialNumber;
	private String itemCode;
	
}
