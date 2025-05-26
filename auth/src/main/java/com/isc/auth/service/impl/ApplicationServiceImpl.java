package com.isc.auth.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.auth.dto.request.ApplicationRequestDTO;
import com.isc.auth.dto.response.ApplicationResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.entitys.ApplicationEntity;
import com.isc.auth.mapper.ApplicationMapper;
import com.isc.auth.mapper.PrivilegeMapper;
import com.isc.auth.repository.ApplicationRepository;
import com.isc.auth.repository.PrivilegesRepository;
import com.isc.auth.service.ApplicationService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;

	@Override
	public ResponseDto<List<ApplicationResponseDTO>> getAll() {
		List<ApplicationResponseDTO> apps = applicationRepository.findAllByActiveTrue().stream()
				.map(ApplicationMapper::toDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Aplicaciones listadas correctamente");

		return new ResponseDto<>(apps, metadata);
	}

	@Override
	public ResponseDto<ApplicationResponseDTO> save(ApplicationRequestDTO request) {
		ApplicationEntity app = new ApplicationEntity();
		app.setName(request.getName());
		app.setDescription(request.getDescription());
		app.setCallback(request.getCallback());
		app.setGitlabRepositoryId(request.getGitlabRepositoryId());
		app.setCurrentVersion(request.getCurrentVersion());
		app.setProjectId(request.getProjectId());
		app = applicationRepository.save(app);
		ApplicationResponseDTO responseDTO = ApplicationMapper.toDto(app);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Aplicacion guardada correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<ApplicationResponseDTO> update(ApplicationRequestDTO request, Integer id) {
		ApplicationEntity app = applicationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Aplicacion no encontrada con ID: " + id));
		app.setName(request.getName());
		app.setDescription(request.getDescription());
		app.setCallback(request.getCallback());
		app.setGitlabRepositoryId(request.getGitlabRepositoryId());
		app.setCurrentVersion(request.getCurrentVersion());
		app.setProjectId(request.getProjectId());
		app.setLastModificationDate(LocalDateTime.now());
		app = applicationRepository.save(app);
		return null;
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  applicationRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  applicationRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
