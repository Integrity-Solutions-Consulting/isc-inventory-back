package com.isc.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    
	@NotNull(message = "La fecha de reparación es obligatoria")
    @PastOrPresent(message = "La fecha de reparación no puede ser futura")
	private LocalDateTime repairDate;
    
	@NotBlank(message = "La descripción es obligatoria")
    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
	private String description;
    
	@NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
	private BigDecimal cost;
    
	@NotBlank(message = "El proveedor del servicio es obligatorio")
    @Size(max = 150, message = "El proveedor no puede superar los 150 caracteres")
	private String serviceProvider;
}
