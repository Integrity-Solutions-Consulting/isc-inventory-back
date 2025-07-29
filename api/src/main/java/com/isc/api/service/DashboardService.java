package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.response.DashboardAcquisitionResponseDTO;
import com.isc.api.dto.response.DashboardEquipmentAssignedByCategoryResponseDTO;
import com.isc.api.dto.response.DashboardEquipmentStatusSummaryResponseDTO;
import com.isc.api.dto.response.DashboardResponseDTO;
import com.isc.dtos.ResponseDto;

public interface DashboardService {
	public ResponseDto<DashboardResponseDTO> getCards();
	public ResponseDto<List<DashboardEquipmentAssignedByCategoryResponseDTO>> getEquipmentAssignedByCategory();
	public ResponseDto<List<DashboardEquipmentStatusSummaryResponseDTO>> getEquipmentStatusSummary(Integer categoryId);
	public ResponseDto<List<DashboardAcquisitionResponseDTO>> getAssetAcquisitionTrends(Integer yearParam);
}
