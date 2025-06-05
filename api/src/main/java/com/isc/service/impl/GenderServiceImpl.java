package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.GenderRequestDTO;
import com.isc.dto.response.GenderDetailResponseDTO;
import com.isc.dto.response.GenderResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.GenderEntity;
import com.isc.mapper.GenderMapper;
import com.isc.repository.GenderRepository;
import com.isc.service.GenderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

	private final GenderRepository genderRepository;

	@Override
	public ResponseDto<List<GenderDetailResponseDTO>> getAllDetails() {
		List<GenderDetailResponseDTO> response = genderRepository.findAll().stream().map(GenderMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Generos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<GenderResponseDTO>> getSimpleList() {
		List<GenderResponseDTO> response = genderRepository.findAllByStatusTrue().stream()
				.map(GenderMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Generos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<GenderDetailResponseDTO> save(GenderRequestDTO request) {
		GenderEntity entity = new GenderEntity();
		entity.setDescription(request.getDescription());
		entity = genderRepository.save(entity);
		GenderDetailResponseDTO response = GenderMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Genero guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<GenderDetailResponseDTO> update(GenderRequestDTO request, Integer id) {
		GenderEntity entity = genderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Genero no encontrado"));
		entity.setDescription(request.getDescription());
		entity.setModificationDate(LocalDateTime.now());
		entity = genderRepository.save(entity);
		GenderDetailResponseDTO response = GenderMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Genero actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  genderRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  genderRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
