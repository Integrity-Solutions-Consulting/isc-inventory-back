package com.isc.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeResponseDTO {
	private Integer id;
	private Integer id_equipment;
	private String conditions;
	private LocalDateTime warrantyStartDate;
	private LocalDateTime warrantyEndDate;
	private String SupportContact;
	private boolean warrantyStatus;
	
}
