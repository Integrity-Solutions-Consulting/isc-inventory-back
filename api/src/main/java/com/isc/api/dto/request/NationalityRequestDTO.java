package com.isc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationalityRequestDTO 
{
	
	private Integer id;
	
	@NotBlank(message = "La nacionalidad no puede estar vacía")
    @Size(max = 100, message = "No puede tener más de 100 caracteres")
	private String description;
}
