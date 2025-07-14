package com.isc.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {
	private Long totalEquipos;
	private Long equiposAsignados;
	private Long clientes;
	private Long equiposEnReparacion;
	private Long equiposDisponibles;
	private Long equiposDeBaja;
}
