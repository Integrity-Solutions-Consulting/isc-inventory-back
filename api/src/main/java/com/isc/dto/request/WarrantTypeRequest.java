package com.isc.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeRequest {
	
	@NotNull(message = "El ID del equipo es obligatorio")
	private Integer id_equipment;
    
	@NotBlank(message = "Las condiciones de garantía no pueden estar vacías")
    @Size(max = 255, message = "Las condiciones no deben superar los 255 caracteres")
	private String conditions;
    
	@NotNull(message = "La fecha de inicio de la garantía es obligatoria")
	private LocalDateTime warrantyStartDate;
    
	@NotNull(message = "La fecha de fin de la garantía es obligatoria")
    @Future(message = "La fecha de fin de la garantía debe estar en el futuro")
	private LocalDateTime warrantyEndDate;
    
	@NotBlank(message = "El contacto de soporte no puede estar vacío")
    @Size(max = 100, message = "El contacto de soporte no debe superar los 100 caracteres")
	private String SupportContact;
    
	@NotNull(message = "El estado de la garantía es obligatorio")
	private boolean warrantyStatus;
}
