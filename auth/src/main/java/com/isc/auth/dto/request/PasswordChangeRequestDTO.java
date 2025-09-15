package com.isc.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequestDTO {
	private String actualPassword;
	
    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 8, max = 30, message = "La nueva contraseña debe tener entre 8 y 30 caracteres")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
        message = "La nueva contraseña debe contener mayúscula, minúscula, número y caracter especial"
    )
	private String newPassword;
    
    @NotBlank(message = "La confirmación de contraseña es obligatoria")
	private String confirmPassword;
}
