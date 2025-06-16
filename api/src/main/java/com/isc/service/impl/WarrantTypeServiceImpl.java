package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.WarrantTypeRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentEntity;
import com.isc.entitys.WarrantTypeEntity;
import com.isc.mapper.WarrantTypeMapper;
import com.isc.repository.EquipmentRepository;
import com.isc.repository.WarrantTypeRepository;
import com.isc.service.WarrantTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarrantTypeServiceImpl implements WarrantTypeService{
	private final WarrantTypeRepository warrantTypeRepository;
	private final EquipmentRepository equipmentRepository;
	
	@Override
	public ResponseDto<List<WarrantTypeDetailResponseDTO>> getAllDetails() {
		List<WarrantTypeDetailResponseDTO> response = warrantTypeRepository.findAll().stream().map(WarrantTypeMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<WarrantTypeResponseDTO>> getSimpleList() {
		List<WarrantTypeResponseDTO> response = warrantTypeRepository.findAllByStatusTrue().stream()
				.map(WarrantTypeMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<WarrantTypeDetailResponseDTO> save(WarrantTypeRequest request) {
		EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
				.orElseThrow(() -> new RuntimeException("Reparacion de equipos no encontrada"));
		
		WarrantTypeEntity entity = new WarrantTypeEntity();
		entity.setEquipment(equipment);
		entity.setConditions(request.getConditions());
		entity.setWarrantyStartDate(request.getWarrantyStartDate());
		entity.setWarrantyEndDate(request.getWarrantyEndDate());
		entity.setSupportContact(request.getSupportContact());
		entity.setWarrantyStatus(request.getWarrantyStatus());
		WarrantTypeDetailResponseDTO response = WarrantTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<WarrantTypeDetailResponseDTO> update(WarrantTypeRequest request, Integer id) {
		WarrantTypeEntity entity = warrantTypeRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		if(entity.getEquipment().getId()!=request.getEquipment()) {
			EquipmentEntity equipment = equipmentRepository
		            .findById(request.getEquipment())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setEquipment(equipment);;
		}
		entity.setConditions(request.getConditions());
		entity.setWarrantyStartDate(request.getWarrantyStartDate());
		entity.setWarrantyEndDate(request.getWarrantyEndDate());
		entity.setSupportContact(request.getSupportContact());
		entity.setWarrantyStatus(request.getWarrantyStatus());
		entity.setModificationDate(LocalDateTime.now());
	    entity = warrantTypeRepository.save(entity);
	    WarrantTypeDetailResponseDTO response = WarrantTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  warrantTypeRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  warrantTypeRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
