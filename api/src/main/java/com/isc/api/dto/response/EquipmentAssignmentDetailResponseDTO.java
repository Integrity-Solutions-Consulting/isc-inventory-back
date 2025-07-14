package com.isc.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentDetailResponseDTO {
	private Integer id;
	
	private EmployeeCatalogResponseDTO employee;
	
	private EquipmentResponseDTO equipment;
	
	private CompanyResponseDTO company;
	
	private LocalDate assignmentDate;
	private LocalDate returnDate;
	private boolean status;
}
