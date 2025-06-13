package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.entitys.ComponentTypeEntity;
import com.isc.entitys.EquipmentCharacteristicEntity;
import com.isc.entitys.IdentificationTypeEntity;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.mapper.EquipmentCharacteristicMapper;
import com.isc.repository.ComponentTypeRepository;
import com.isc.repository.EquipmentCharacteristicRepository;
import com.isc.service.EquipmentCharacteristicService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCharacteristicServiceImpl implements EquipmentCharacteristicService{
	private final EquipmentCharacteristicRepository equipmentCharacteristicRepository;
	private final ComponentTypeRepository componentTypeRepository;
	
	@Override
	public ResponseDto<List<EquipmentCharacteristicDetailResponseDTO>> getAllDetails(){
		List<EquipmentCharacteristicDetailResponseDTO> response = equipmentCharacteristicRepository.findAll().stream().map(EquipmentCharacteristicMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentCharacteristicResponseDTO>> getSimpleList(){
		List<EquipmentCharacteristicResponseDTO> response = equipmentCharacteristicRepository.findAllByStatusTrue().stream()
				.map(EquipmentCharacteristicMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Generos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> save(EquipmentCharacteristicRequestDTO request){
		ComponentTypeEntity componentType = componentTypeRepository.findById(request.getComponent())
				.orElseThrow(() -> new RuntimeException("Tipo de componente no encontrada"));		
		EquipmentCharacteristicEntity entity = new EquipmentCharacteristicEntity();
		entity.setDescription(request.getDescription());
		entity = equipmentCharacteristicRepository.save(entity);
		EquipmentCharacteristicDetailResponseDTO response = EquipmentCharacteristicMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Genero guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCharacteristicDetailResponseDTO> update(EquipmentCharacteristicRequestDTO request, Integer id){
		EquipmentCharacteristicEntity entity = equipmentCharacteristicRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Genero no encontrado"));
		if(entity.getComponent().getId()!=request.getComponent()) {
			ComponentTypeEntity componentType = componentTypeRepository.findById(request.getComponent())
		            .orElseThrow(() -> new RuntimeException("Tipo de componente no encontrado"));
		    entity.setComponent(componentType);
		}		
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = equipmentCharacteristicRepository.save(entity);
		EquipmentCharacteristicDetailResponseDTO response = EquipmentCharacteristicMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Genero actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentCharacteristicRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentCharacteristicRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
}
