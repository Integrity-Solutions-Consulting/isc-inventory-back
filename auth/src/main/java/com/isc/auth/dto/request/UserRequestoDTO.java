package com.isc.auth.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@Pattern(regexp = "^[a-zA-Z0-9._-]{1,50}$", message = "El nombre de usuario solo puede contener letras, números, puntos, guiones y guiones bajos")
	private String username;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe proporcionar un email válido")
	@Size(max = 50)
	private String email;
	
	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 100)
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$", message = "Los nombres solo pueden contener letras y espacios")
	private String firstNames;

	@NotNull(message = "El ID del empleado es obligatorio")
	private Integer employeeId;
	
	@NotEmpty(message = "Debe asignar al menos un rol al usuario")
	private Set<Integer> rolesId;
	
	private Set<Integer> privilegesId;
	
	private Set<Integer> menusId;
	
	public void setUsername(String username) {
	    if (username != null) {
	        // Elimina caracteres Unicode de control (incluye espacios de ancho cero)
	        username = username.replaceAll("\\p{C}", "");
	        // Elimina espacios al inicio y fin
	        username = username.trim();
	    }
	    this.username = username;
	}
}
