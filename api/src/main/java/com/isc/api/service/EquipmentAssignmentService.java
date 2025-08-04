package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.api.dto.request.EquipmentRevokeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dtos.ResponseDto;

import net.sf.jasperreports.engine.JRException;

public interface EquipmentAssignmentService {
	public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList();
	ResponseDto<List<Integer>> getAvailableEquipmentIds();
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> assign(EquipmentAssignmentRequestDTO request);
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> revoke(Integer id, EquipmentRevokeRequestDTO request);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
	public byte[] generateReport(Integer id) throws JRException;
}
