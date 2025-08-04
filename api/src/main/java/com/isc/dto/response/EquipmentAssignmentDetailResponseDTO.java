package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentDetailResponseDTO {
	private Integer id;
	
	private Integer employee;
	private Integer identificationType;
	private String fullName;
	
	private Integer equipment;
	private String serialNumber;
	
	private LocalDateTime assignmentDate;
	private LocalDateTime returnDate;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	
}
