package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.response.DashboardEquipmentAssignedByCategoryResponseDTO;
import com.isc.api.dto.response.DashboardResponseDTO;
import com.isc.dtos.ResponseDto;

public interface DashboardService {
	public ResponseDto<DashboardResponseDTO> getCards();
	public ResponseDto<List<DashboardEquipmentAssignedByCategoryResponseDTO>> getEquipmentAssignedByCategory();
}
