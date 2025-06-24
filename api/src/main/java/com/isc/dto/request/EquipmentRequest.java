package com.isc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequest {
	
	
	private Integer invoice;
    
	@NotNull(message = "El estado del equipo es obligatorio")
	private Integer EquipStatus;
    
	@NotNull(message = "La categoria es obligatoria")
	private Integer category;
    
	@NotNull(message = "La empresa es obligatoria")
	private Integer company;
    
	@NotNull(message = "La caracteristica es obligatoria")
	private Integer characteristic;
    
	@NotBlank(message = "La marca es obligatoria")
    @Size(max = 100, message = "La marca no puede superar los 100 caracteres")
	private String brand;
    
	@NotBlank(message = "El modelo es obligatoria")
    @Size(max = 100, message = "El modelo no puede superar los 100 caracteres")
	private String model;
    
	@NotBlank(message = "El número de serie es obligatorio")
    @Size(max = 100, message = "El número de serie no puede superar los 100 caracteres")
	private String SerialNumber;
    
	@NotBlank(message = "El codigo del item es obligatorio")
    @Size(max = 100, message = "El codigo del item no puede superar los 100 caracteres")
	private String itemCode;
}
