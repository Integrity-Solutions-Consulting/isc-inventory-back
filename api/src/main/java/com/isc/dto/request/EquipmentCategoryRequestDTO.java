package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryRequestDTO {
	
	@NotBlank(message = "El nombre del equipo no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
	private String name;
}
