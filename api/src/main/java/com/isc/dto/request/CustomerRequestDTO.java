package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
	
	@NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
	private String name;
    
	@Size(max = 255, message = "El nombre no puede tener más de 100 caracteres")
	private String address;
    
	@NotBlank(message = "El email del cliente es obligatorio")
    @Size(max = 150, message = "El email no puede tener más de 150 caracteres")
	private String email;
    
	@Pattern(
	        regexp = "^\\+?[0-9\\s\\-]{7,20}$",
	        message = "El número de teléfono debe contener solo dígitos, espacios o guiones, y tener entre 7 y 20 caracteres"
	    )
	private String phone;
}
