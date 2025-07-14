package com.isc.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
	
	@NotBlank(message = "El nombre del negocio es obligatorio")
    @Size(max = 150, message = "El nombre del negocio no puede exceder los 150 caracteres")
	private String businessName;
    
	@NotBlank(message = "La direccion es obligatoria")
    @Size(max = 200, message = "La direccion no puede exceder los 200 caracteres")
	private String address;
    
	@NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
        regexp = "^[0-9\\-\\+]{7,15}$",
        message = "El teléfono debe tener entre 7 y 15 dígitos y puede incluir + o -"
    )
	private String phone;
    
	@NotBlank(message = "El correo electrónico es obligatorio")
	@Size(max = 100, message = "El correo no debe superar los 100 caracteres")
    @Email(message = "El correo electrónico no tiene un formato válido")
	private String email;
    
	@NotBlank(message = "El RUC es obligatorio")
    @Size(max = 50, message = "El RUC no puede exceder los 50 caracteres")
	private String taxId;
}
