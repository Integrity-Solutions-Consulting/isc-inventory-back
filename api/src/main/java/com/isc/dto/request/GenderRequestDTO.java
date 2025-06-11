package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderRequestDTO {
	
	@NotBlank(message = "La descripcion del genero no puede estar vacía")
    @Size(max = 50, message = "La descripcion no puede tener más de 50 caracteres")
	private String description;
}
