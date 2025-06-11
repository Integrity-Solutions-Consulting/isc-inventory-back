package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentAssignmentService {
	public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> save(EquipmentAssignmentRequestDTO request);
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> update(EquipmentAssignmentRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
