package com.isc.auth.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestoDTO {
	@NotBlank(message = "El nombre de usuario es obligatorio")
	@Size(max = 50, message = "El nombre de usuario no puede tener más de 50 caracteres")
	private String username;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe proporcionar un email válido")
	@Size(max = 50)
	private String email;
	
	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 100)
	private String firstNames;

	@NotNull(message = "El ID del empleado es obligatorio")
	private Integer employeeId;
	
	@NotEmpty(message = "Debe asignar al menos un rol al usuario")
	private Set<Integer> rolesId;
	
	private Set<Integer> privilegesId;
}
