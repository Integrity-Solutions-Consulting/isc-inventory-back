package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionRequestDTO {
	
	@NotBlank(message = "El nombre de la posicion es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
	private String name;
	
	@NotBlank(message = "La descripcion de la posicion no puede estar vacía")
    @Size(max = 100, message = "La descripcion no puede tener más de 100 caracteres")
	private String description;
}
