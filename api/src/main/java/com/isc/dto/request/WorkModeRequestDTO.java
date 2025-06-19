package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkModeRequestDTO {
	
	@NotBlank(message = "El nombre del modo de trabajo no puede estar vacío")
    @Size(min=1,max = 100, message = "El nombre no puede tener más de 100 caracteres")
	private String name;
	
	@NotBlank(message = "La descripcion del modo de trabajo  no puede estar vacía")
    @Size(max = 100, message = "La descripcion no puede tener más de 100 caracteres")
	private String description;
}
