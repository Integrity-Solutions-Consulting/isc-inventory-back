package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentCharacteristicEntity;

public interface EquipmentCharacteristicService {
	public ResponseDto<List<EquipmentCharacteristicDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCharacteristicResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> save(EquipmentCharacteristicRequestDTO request);
	public EquipmentCharacteristicEntity saveForEquipment(EquipmentCharacteristicRequestDTO request);
	public EquipmentCharacteristicEntity updateForEntity(EquipmentCharacteristicRequestDTO request);
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> update(EquipmentCharacteristicRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
