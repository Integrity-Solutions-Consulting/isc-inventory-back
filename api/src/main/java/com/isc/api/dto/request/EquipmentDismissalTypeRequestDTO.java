package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDismissalTypeRequestDTO 
{
	private Integer id;
	
	@NotBlank(message = "El tipo de baja es obligatorio")
    @Size(max = 150, message = "El tipo de baja no puede exceder los 150 caracteres")
    private String name;

}
