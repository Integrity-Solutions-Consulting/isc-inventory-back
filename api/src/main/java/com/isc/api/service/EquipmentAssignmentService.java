package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.request.EquipmentRevokeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentAssignmentService {
	public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> assign(EquipmentAssignmentRequestDTO request);
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> revoke(Integer id, EquipmentRevokeRequestDTO request);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
