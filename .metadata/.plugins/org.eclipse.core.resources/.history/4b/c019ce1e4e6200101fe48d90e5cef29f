package com.isc.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.response.DashboardResponseDTO;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.service.DashboardService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private final EquipmentRepository equipmentRepository;

	@Override
	public ResponseDto<DashboardResponseDTO> getCards() {
		List<Object[]> result = equipmentRepository.getDashboardTotalsRaw();
		if (result.isEmpty()) {
			return null; // o devolver un DTO vacío
		}
		Object[] row = result.get(0);
		DashboardResponseDTO response = new DashboardResponseDTO( );
		response.setTotalEquipos(((Number) row[0]).longValue());
		response.setEquiposAsignados(((Number) row[1]).longValue());
		response.setClientes(((Number) row[2]).longValue());
		response.setEquiposEnReparacion(((Number) row[3]).longValue());
		response.setEquiposDisponibles(((Number) row[4]).longValue());
		response.setEquiposDeBaja(((Number) row[5]).longValue());
		
		
		
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empleados listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

}
