package com.isc.dto.request;

import java.util.List;

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
	@NotNull(message = "La condicion del equipo es obligatorio")
	private Integer condition;
    
	private Integer categoryId;
	
	@NotBlank
	private String categoryName;
    
	@NotNull(message = "La empresa es obligatoria")
	private Integer company;
    //onetomanny
	@NotNull(message = "La caracteristica es obligatoria")
	private List<EquipmentCharacteristicRequestDTO> equipmentCharacteristics;
    
	@NotBlank(message = "La marca es obligatoria")
    @Size(max = 100, message = "La marca no puede superar los 100 caracteres")
	private String brand;
    
	@NotBlank(message = "El modelo es obligatoria")
    @Size(max = 100, message = "El modelo no puede superar los 100 caracteres")
	private String model;
    
	@NotBlank(message = "El número de serie es obligatorio")
    @Size(max = 100, message = "El número de serie no puede superar los 100 caracteres")
	private String serialNumber;
    
	@NotBlank(message = "El codigo del item es obligatorio")
    @Size(max = 100, message = "El codigo del item no puede superar los 100 caracteres")
	private String itemCode;
}
