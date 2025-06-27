package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.IdentificationTypeRequestDTO;
import com.isc.api.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.api.dto.response.IdentificationTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.IdentificationTypeEntity;
import com.isc.api.mapper.IdentificationTypeMapper;
import com.isc.api.repository.IdentificationTypeRepository;
import com.isc.api.service.IdentificationTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentificationTypeServiceImpl implements IdentificationTypeService{
	private final IdentificationTypeRepository identificationTypeRepository;

	@Override
	public ResponseDto<List<IdentificationTypeDetailResponseDTO>> getAllDetails() {
		List<IdentificationTypeDetailResponseDTO> response = identificationTypeRepository.findAll().stream().map(IdentificationTypeMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de identifiacion listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<IdentificationTypeResponseDTO>> getSimpleList() {
		List<IdentificationTypeResponseDTO> response = identificationTypeRepository.findAllByStatusTrue().stream()
				.map(IdentificationTypeMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de identifiacion listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<IdentificationTypeDetailResponseDTO> save(IdentificationTypeRequestDTO request) {
		IdentificationTypeEntity entity = new IdentificationTypeEntity();
		entity.setDescription(request.getDescription());
		entity = identificationTypeRepository.save(entity);
		IdentificationTypeDetailResponseDTO response = IdentificationTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de identifiacion guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<IdentificationTypeDetailResponseDTO> update(IdentificationTypeRequestDTO request, Integer id) {
		IdentificationTypeEntity entity = identificationTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Genero no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = identificationTypeRepository.save(entity);
		IdentificationTypeDetailResponseDTO response = IdentificationTypeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de identifiacion actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}


	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  identificationTypeRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  identificationTypeRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
}
