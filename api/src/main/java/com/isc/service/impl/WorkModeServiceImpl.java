package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.WorkModeRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WorkModeDetailResponseDTO;
import com.isc.dto.response.WorkModeResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.WorkModeEntity;
import com.isc.mapper.WorkModeMapper;
import com.isc.repository.WorkModeRepository;
import com.isc.service.WorkModeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkModeServiceImpl implements WorkModeService{
	
	private final WorkModeRepository workModeRepository;
	
	@Override
	public ResponseDto<List<WorkModeDetailResponseDTO>> getAllDetails() {
		List<WorkModeDetailResponseDTO> response = workModeRepository.findAll().stream().map(WorkModeMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<WorkModeResponseDTO>> getSimpleList() {
		List<WorkModeResponseDTO> response = workModeRepository.findAllByStatusTrue().stream()
				.map(WorkModeMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<WorkModeDetailResponseDTO> save(WorkModeRequestDTO request) {
		WorkModeEntity entity = new WorkModeEntity();
		entity.setDescription(request.getDescription());
		entity = workModeRepository.save(entity);
		WorkModeDetailResponseDTO response = WorkModeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<WorkModeDetailResponseDTO> update(WorkModeRequestDTO request, Integer id) {
		WorkModeEntity entity = workModeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Modo de trabajo no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = workModeRepository.save(entity);
		WorkModeDetailResponseDTO response = WorkModeMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Modos de trabajo actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  workModeRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  workModeRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
