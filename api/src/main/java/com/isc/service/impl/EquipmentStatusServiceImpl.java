package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentStatusRequestDTO;
import com.isc.dto.response.EquipmentStatusDetailResponseDTO;
import com.isc.dto.response.EquipmentStatusResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentStatusEntity;
import com.isc.mapper.EquipmentStatusMapper;
import com.isc.repository.EquipmentStatusRepository;
import com.isc.service.EquipmentStatusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentStatusServiceImpl implements EquipmentStatusService {
	private final EquipmentStatusRepository equipmentStatusRepository;

	@Override
	public ResponseDto<List<EquipmentStatusDetailResponseDTO>> getAllDetails() {
		List<EquipmentStatusDetailResponseDTO> response = equipmentStatusRepository.findAll().stream().map(EquipmentStatusMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Estados de los equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<EquipmentStatusResponseDTO>> getSimpleList() {
		List<EquipmentStatusResponseDTO> response = equipmentStatusRepository.findAllByStatusTrue().stream()
				.map(EquipmentStatusMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Estados de los equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EquipmentStatusDetailResponseDTO> save(EquipmentStatusRequestDTO request) {
		EquipmentStatusEntity entity = new EquipmentStatusEntity();
		entity.setName(request.getName());
		entity = equipmentStatusRepository.save(entity);
		EquipmentStatusDetailResponseDTO response = EquipmentStatusMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Estado del equipo guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EquipmentStatusDetailResponseDTO> update(EquipmentStatusRequestDTO request, Integer id) {
		EquipmentStatusEntity entity = equipmentStatusRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Estado del equipo no encontrado"));
		entity.setName(request.getName());
		entity = equipmentStatusRepository.save(entity);
		EquipmentStatusDetailResponseDTO response = EquipmentStatusMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Estado del equipo guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  equipmentStatusRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  equipmentStatusRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
