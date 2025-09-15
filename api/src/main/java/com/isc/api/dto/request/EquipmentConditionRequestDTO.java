package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentConditionRequestDTO 
{	
	@NotBlank(message = "El tipo condicion no puede estar vacío")
    @Size(max = 255, message = "El tipo de condicion no puede tener más de 255 caracteres")
	private String name;

}
