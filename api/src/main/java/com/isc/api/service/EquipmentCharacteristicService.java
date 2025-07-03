package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.EquipmentCharacteristicEntity;
import com.isc.api.entitys.EquipmentEntity;

public interface EquipmentCharacteristicService {
	public ResponseDto<List<EquipmentCharacteristicDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCharacteristicResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> save(EquipmentCharacteristicRequestDTO request);
	public EquipmentCharacteristicEntity saveForEquipment(EquipmentCharacteristicRequestDTO request, EquipmentEntity equipment);
	public EquipmentCharacteristicEntity updateForEntity(EquipmentCharacteristicRequestDTO request);
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> update(EquipmentCharacteristicRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
