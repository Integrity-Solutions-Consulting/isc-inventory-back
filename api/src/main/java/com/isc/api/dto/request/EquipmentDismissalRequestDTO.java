package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message = "El tipo de baja es obligatorio")
    private Integer dismissalTypeId; 
    
}
