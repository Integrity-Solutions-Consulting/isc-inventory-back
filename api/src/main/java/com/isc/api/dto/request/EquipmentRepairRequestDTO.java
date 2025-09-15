package com.isc.api.dto.request;

import java.math.BigDecimal;

import com.isc.api.dto.response.EquipmentConditionResponseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRepairRequestDTO {
	
	@NotNull(message = "El ID del equipo es obligatorio")
	private Integer equipment;
    
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
	private String description;
    
    @NotNull(message = "El ID del proveedor es obligatorio")
    private Integer serviceProviderId;
	
	private BigDecimal cost;
	
	private boolean revoke;
	
	private EquipmentConditionResponseDTO condition;
	
}