package com.isc.auth.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
	
	@NotBlank(message = "El nombre del rol es obligatorio")
	@Size(max = 50, message = "El nombre del rol no puede tener más de 50 caracteres")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\\s\\-_.]+$", message = "El nombre del rol contiene caracteres no válidos")
	private String name;

	@Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
	private String description;

	@NotNull(message = "El ID de la aplicación es obligatorio")
	private Integer applicationId;
	
	private Set<Integer> privilegesId;
	private Set<Integer> menusId;
}
