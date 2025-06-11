package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentTypeRequestDTO {
	
	@NotBlank(message = "La descripcion del componenete no puede estar vacía")
    @Size(max = 100, message = "La descripcion no puede tener más de 100 caracteres")
	private String description;
}
