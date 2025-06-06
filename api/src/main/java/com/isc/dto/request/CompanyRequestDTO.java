package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {
	
	@NotBlank(message = "El nombre de la empresa no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
	private String name;
	
	@NotBlank(message = "La descripcion de la empresa no puede estar vacía")
    @Size(max = 255, message = "La descripcion no puede tener más de 255 caracteres")
	private String description;
}
