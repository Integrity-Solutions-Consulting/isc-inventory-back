package com.isc.api.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDismissalRequestDTO 
{
	private Integer equipmentId; 
	
    private Integer dismissalTypeId; 
    
    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String reason;
}
