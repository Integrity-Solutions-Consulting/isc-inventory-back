package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.ComponentTypeEntity;
import com.isc.mapper.ComponentTypeMapper;
import com.isc.repository.ComponentTypeRepository;
import com.isc.service.ComponentTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComponentTypeServiceImpl implements ComponentTypeService{
	private final ComponentTypeRepository componentTypeRepository;

	@Override
	public ResponseDto<List<ComponentTypeDetailResponseDTO>> getAllDetails() {
		List<ComponentTypeDetailResponseDTO> response = componentTypeRepository.findAll().stream().map(ComponentTypeMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<ComponentTypeResponseDTO>> getSimpleList() {
		List<ComponentTypeResponseDTO> response = componentTypeRepository.findAllByStatusTrue().stream()
				.map(ComponentTypeMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<ComponentTypeDetailResponseDTO> save(ComponentTypeRequestDTO request) {
		ComponentTypeEntity entity = new ComponentTypeEntity();
		entity.setDescription(request.getDescription());
		entity = componentTypeRepository.save(entity);
		ComponentTypeDetailResponseDTO response = ComponentTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<ComponentTypeDetailResponseDTO> update(ComponentTypeRequestDTO request, Integer id) {
		ComponentTypeEntity entity = componentTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nacionalidad no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = componentTypeRepository.save(entity);
		ComponentTypeDetailResponseDTO response = ComponentTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  componentTypeRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  componentTypeRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
}
