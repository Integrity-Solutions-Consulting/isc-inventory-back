package com.isc.api.dto.request;

import java.time.LocalDate;
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
    
	@NotNull(message = "La fecha de asignacion del equipo es obligatorio")
	private LocalDate assigmentDate;
}
