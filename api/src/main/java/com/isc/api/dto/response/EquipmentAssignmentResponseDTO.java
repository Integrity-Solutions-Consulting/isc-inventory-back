package com.isc.api.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentResponseDTO {
	private Integer id;
	private Integer employee;
	private Integer equipment;
	private LocalDate assignmentDate;
	private LocalDate returnDate;
	
}
