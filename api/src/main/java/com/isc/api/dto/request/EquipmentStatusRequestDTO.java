package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentStatusRequestDTO {
	
	@NotBlank(message = "El nombre del estado no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
	private String name;
	
}
