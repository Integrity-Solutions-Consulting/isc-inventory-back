package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplierTypeRequestDTO 
{
	private Integer id;
	
	@NotBlank(message = "El tipo de proveedor es obligatorio")
    @Size(max = 150, message = "El tipo de proveedor no puede exceder los 50 caracteres")
    private String name;
}
