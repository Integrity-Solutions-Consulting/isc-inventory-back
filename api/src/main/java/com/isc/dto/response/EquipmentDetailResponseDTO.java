package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDetailResponseDTO {
	private Integer id;
	private Integer invoice;
	private Integer EquipStatus;
	private Integer category;
	private Integer company;
	private Integer characteristic;
	private String brand;
	private String model;
	private String SerialNumber;
	private String itemCode;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
