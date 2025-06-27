package com.isc.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentRequestDTO {
	
	@NotNull(message = "El ID del empleado es obligatorio")
	private Integer employee;
    
	@NotNull(message = "El ID del equipo es obligatorio")
	private Integer equipment;
    
    @FutureOrPresent(message = "La fecha de asignación debe ser en el presente o en el futuro")
	private LocalDateTime assigmentDate;
}
