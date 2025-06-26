package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.dto.request.EmployeeRequestDTO;
import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeDetailResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.GenderEntity;
import com.isc.entitys.IdentificationTypeEntity;
import com.isc.entitys.NationalityEntity;
import com.isc.entitys.PositionEntity;
import com.isc.entitys.WorkModeEntity;
import com.isc.mapper.EmployeeMapper;
import com.isc.repository.EmployeeRepository;
import com.isc.repository.GenderRepository;
import com.isc.repository.IdentificationTypeRepository;
import com.isc.repository.NationalityRepository;
import com.isc.repository.PositionRepository;
import com.isc.repository.WorkModeRepository;
import com.isc.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final IdentificationTypeRepository identificationTypeRepository;
	private final NationalityRepository nationalityRepository;
	private final GenderRepository genderRepository;
	private final PositionRepository positionRepository;
	private final WorkModeRepository workModeRepository;

	@Override
	@Transactional
	public ResponseDto<List<EmployeeTableResponseDTO>> getAllTable() {
		List<EmployeeTableResponseDTO> response = employeeRepository.findAllEmployeeTableData();
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empleados listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<EmployeeCatalogResponseDTO>> getSimpleList() {
		List<EmployeeCatalogResponseDTO> response = employeeRepository.findAllActiveEmployeeCatalog();
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empleados listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EmployeeDetailResponseDTO> getInfoById(Integer id) {
		EmployeeEntity e = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		EmployeeDetailResponseDTO response = EmployeeMapper.toDetailResponse(e);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EmployeeTableResponseDTO> save(EmployeeRequestDTO request) {
		IdentificationTypeEntity identificationType = identificationTypeRepository
				.findById(request.getIdIdentificationType())
				.orElseThrow(() -> new RuntimeException("Tipo de identificacion no encontrada"));
		GenderEntity gender = genderRepository.findById(request.getIdGender())
				.orElseThrow(() -> new RuntimeException("Genero no encontrado"));
		NationalityEntity nationality = nationalityRepository
				.findById(request.getIdNationality())
				.orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada"));
		PositionEntity position = positionRepository
				.findById(request.getIdPosition())
				.orElseThrow(() -> new RuntimeException("Posicion no encontrada"));
		WorkModeEntity workMode = workModeRepository
				.findById(request.getIdWorkMode())
				.orElseThrow(() -> new RuntimeException("Modo de trabajo no encontrado"));
		EmployeeEntity entity = new EmployeeEntity();
		entity.setIdentificationType(identificationType);
		entity.setGender(gender);
		entity.setNationality(nationality);
		entity.setPosition(position);
		entity.setWorkMode(workMode);
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setIdentification(request.getIdentification());
		entity.setPhone(request.getPhone());
		entity.setEmail(request.getEmail());
		entity.setAddress(request.getAddress());
		entity.setContractDate(request.getContractDate());
		entity.setContractEndDate(request.getContractEndDate());
		entity = employeeRepository.save(entity);
		EmployeeTableResponseDTO response = EmployeeMapper.toTableResponse(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EmployeeTableResponseDTO> update(EmployeeRequestDTO request, Integer id) {
		EmployeeEntity entity = employeeRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		if(entity.getIdentificationType().getId()!=request.getIdIdentificationType()) {
		    IdentificationTypeEntity identificationType = identificationTypeRepository
		            .findById(request.getIdIdentificationType())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setIdentificationType(identificationType);
		}
		if(entity.getGender().getId()!=request.getIdGender()) {
		    GenderEntity gender = genderRepository.findById(request.getIdGender())
		            .orElseThrow(() -> new RuntimeException("Género no encontrado"));
		    entity.setGender(gender);
		}
		if(entity.getNationality().getId()!=request.getIdNationality()) {
			NationalityEntity nationality = nationalityRepository
		            .findById(request.getIdNationality())
		            .orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada"));
		    entity.setNationality(nationality);
		}
		if(entity.getPosition().getId()!=request.getIdPosition()) {
			 PositionEntity position = positionRepository
			            .findById(request.getIdPosition())
			            .orElseThrow(() -> new RuntimeException("Posición no encontrada"));
			 entity.setPosition(position);
		}
		if(entity.getWorkMode().getId()!=request.getIdWorkMode()) {
			 WorkModeEntity workMode = workModeRepository
			            .findById(request.getIdWorkMode())
			            .orElseThrow(() -> new RuntimeException("Modo de trabajo no encontrado"));
			 entity.setWorkMode(workMode);
		}

	    entity.setFirstName(request.getFirstName());
	    entity.setLastName(request.getLastName());
	    entity.setIdentification(request.getIdentification());
	    entity.setPhone(request.getPhone());
	    entity.setEmail(request.getEmail());
	    entity.setAddress(request.getAddress());
	    entity.setContractDate(request.getContractDate());
	    entity.setContractEndDate(request.getContractEndDate());
	    
	    entity.setModificationDate(LocalDateTime.now());
	    entity = employeeRepository.save(entity);
	    EmployeeTableResponseDTO response = EmployeeMapper.toTableResponse(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  employeeRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  employeeRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
