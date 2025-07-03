package com.isc.auth.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.auth.dto.request.PrivilegeRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.mapper.PrivilegeMapper;
import com.isc.auth.repository.PrivilegeRoleRepository;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.service.PrivilegeService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
	
	private final PrivilegesRepository privilegiesRepository;

	@Override
	public ResponseDto<List<PrivilegeResponseDTO>> getAll() {
		List<PrivilegeResponseDTO> privileges = privilegiesRepository.findAllByActiveTrue().stream().map(PrivilegeMapper::toDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Privilegios listados correctamente");

		return new ResponseDto<>(privileges, metadata);
	}

	@Override
	public ResponseDto<PrivilegeResponseDTO> save(PrivilegeRequestDTO request) {
		PrivilegeEntity privilege = new PrivilegeEntity();
		privilege.setKey(request.getKey());
		privilege.setDescription(request.getDescription());
		privilege.setApplicationId(request.getApplicationId());
		privilege = privilegiesRepository.save(privilege);
		PrivilegeResponseDTO responseDTO = PrivilegeMapper.toDto(privilege);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Privilegio guardado correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactivePrivilege(Integer id) {
		int rowsAffected =  privilegiesRepository.inactivePrivilege(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> activePrivilege(Integer id) {
		int rowsAffected =  privilegiesRepository.activePrivilege(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
}
