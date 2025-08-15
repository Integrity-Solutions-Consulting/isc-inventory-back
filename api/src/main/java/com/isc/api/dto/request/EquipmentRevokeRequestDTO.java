package com.isc.api.dto.request;

import java.time.LocalDate;

import com.isc.api.dto.response.EquipmentConditionResponseDTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EquipmentRevokeRequestDTO {
	@FutureOrPresent(message = "La fecha de revocación debe ser en el presente o en el futuro")
	private LocalDate revokeDate;
	
	private EquipmentConditionResponseDTO condition;
	
	@Size(max=150,message="Las observaciones tienen que tener maximo 150 caracteres")
    private String observations;
}
