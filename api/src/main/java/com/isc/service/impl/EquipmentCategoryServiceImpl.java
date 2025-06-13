package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentCategoryRequestDTO;
import com.isc.entitys.EquipmentCategoryEntity;
import com.isc.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.mapper.EquipmentCategoryMapper;
import com.isc.repository.EquipmentCategoryRepository;
import com.isc.service.EquipmentCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService{
	private final EquipmentCategoryRepository equipmentCategoryRepository;
	
	@Override
	public ResponseDto<List<EquipmentCategoryDetailResponseDTO>> getAllDetails(){
		List<EquipmentCategoryDetailResponseDTO> response = equipmentCategoryRepository.findAll().stream().map(EquipmentCategoryMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentCategoryResponseDTO>> getSimpleList(){
		List<EquipmentCategoryResponseDTO> response = equipmentCategoryRepository.findAllByStatusTrue().stream()
				.map(EquipmentCategoryMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCategoryDetailResponseDTO> save(EquipmentCategoryRequestDTO request){
		EquipmentCategoryEntity entity = new EquipmentCategoryEntity();
		entity.setName(request.getName());
		entity = equipmentCategoryRepository.save(entity);
		EquipmentCategoryDetailResponseDTO response = EquipmentCategoryMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria de equipo guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCategoryDetailResponseDTO> update(EquipmentCategoryRequestDTO request, Integer id){
		EquipmentCategoryEntity entity = equipmentCategoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria de equipo no encontrado"));
		entity.setName(request.getName());
		entity.setModificationDate(LocalDateTime.now());
		entity = equipmentCategoryRepository.save(entity);
		EquipmentCategoryDetailResponseDTO response = EquipmentCategoryMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria de equipos actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentCategoryRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentCategoryRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
}
