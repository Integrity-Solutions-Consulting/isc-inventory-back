package com.isc.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRevokeRequestDTO {
	@FutureOrPresent(message = "La fecha de revocación debe ser en el presente o en el futuro")
	private LocalDate revokeDate;
}
