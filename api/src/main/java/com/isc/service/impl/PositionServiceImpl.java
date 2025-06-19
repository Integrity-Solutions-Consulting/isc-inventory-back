package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.PositionRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.PositionDetailResponseDTO;
import com.isc.dto.response.PositionResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.PositionEntity;
import com.isc.mapper.PositionMapper;
import com.isc.repository.PositionRepository;
import com.isc.service.PositionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{
	
	private final PositionRepository positionRepository;

	@Override
	public ResponseDto<List<PositionDetailResponseDTO>> getAllDetails() {
		List<PositionDetailResponseDTO> response = positionRepository.findAll().stream().map(PositionMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Posiciones listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<PositionResponseDTO>> getSimpleList() {
		List<PositionResponseDTO> response = positionRepository.findAllByStatusTrue().stream()
				.map(PositionMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Posiciones listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<PositionDetailResponseDTO> save(PositionRequestDTO request) {
		PositionEntity entity = new PositionEntity();
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity = positionRepository.save(entity);
		PositionDetailResponseDTO response = PositionMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Posiciones guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<PositionDetailResponseDTO> update(PositionRequestDTO request, Integer id) {
		PositionEntity entity = positionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Posicion no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = positionRepository.save(entity);
		PositionDetailResponseDTO response = PositionMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Posiciones actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  positionRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  positionRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
