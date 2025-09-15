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
public class PrivilegeRequestDTO {

    @NotBlank(message = "La clave del privilegio es obligatoria")
    @Size(max = 100, message = "La clave del privilegio no puede tener más de 100 caracteres")
    @Pattern(
        regexp = "^[A-Z_][A-Z_]*$",
        message = "La clave debe estar en mayúsculas y solo puede contener letras y guiones bajos"
    )
    private String key;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String description;

    @NotNull(message = "El ID de la aplicación es obligatorio")
    private Integer applicationId;
}
