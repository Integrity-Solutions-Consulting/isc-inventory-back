package com.isc.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    
    @NotNull(message = "El tipo de identificación es obligatorio")
    @Size(max = 13, message = "La identificacion no puede tener más de 13 caracteres")
    private String ruc;

    private String address;

    @Pattern(
	        regexp = "^\\+?[0-9\\s\\-]{7,10}$",
	        message = "El teléfono debe tener entre 7 y 10 caracteres, y contener solo dígitos, espacios o guiones"
	    )
	private String phone;

    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 100, message = "El correo electrónico no puede tener más de 100 caracteres")
	private String email;
}
