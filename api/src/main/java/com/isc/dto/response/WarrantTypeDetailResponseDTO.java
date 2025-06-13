package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeDetailResponseDTO {
	private Integer id;
	
	private Integer equipment;
	private String serialNumber;
	
	private String conditions;
	private LocalDateTime warrantyStartDate;
	private LocalDateTime warrantyEndDate;
	private String supportContact;
	private Short warrantyStatus;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
}
