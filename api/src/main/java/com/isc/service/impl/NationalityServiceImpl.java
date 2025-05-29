package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.NationalityRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.NationalityDetailResponseDTO;
import com.isc.dto.response.NationalityResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.NationalityEntity;
import com.isc.mapper.NationalityMapper;
import com.isc.repository.NationalityRepository;
import com.isc.service.NationalityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NationalityServiceImpl implements NationalityService {
	
	private final NationalityRepository nationalityRepository;

	@Override
	public ResponseDto<List<NationalityDetailResponseDTO>> getAllDetails() {
		List<NationalityDetailResponseDTO> response = nationalityRepository.findAll().stream().map(NationalityMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<NationalityResponseDTO>> getSimpleList() {
		List<NationalityResponseDTO> response = nationalityRepository.findAllByActiveTrue().stream()
				.map(NationalityMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<NationalityDetailResponseDTO> save(NationalityRequestDTO request) {
		NationalityEntity entity = new NationalityEntity();
		entity.setDescription(request.getDescription());
		entity = nationalityRepository.save(entity);
		NationalityDetailResponseDTO response = NationalityMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<NationalityDetailResponseDTO> update(NationalityRequestDTO request, Integer id) {
		NationalityEntity entity = nationalityRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nacionalidad no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = nationalityRepository.save(entity);
		NationalityDetailResponseDTO response = NationalityMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Nacionalidades actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  nationalityRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  nationalityRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}


}
