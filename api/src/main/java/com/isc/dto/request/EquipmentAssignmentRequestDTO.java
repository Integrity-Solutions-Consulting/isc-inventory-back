package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentRequestDTO {
	private Integer employee;
    private Integer equipment;
    private Integer assigmentDate;
    private Integer returnDate;
}
