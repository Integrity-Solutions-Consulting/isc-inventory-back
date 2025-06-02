package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EmployeeRequestDTO;
import com.isc.dto.request.GenderRequestDTO;
import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeDetailResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.dto.response.GenderDetailResponseDTO;
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
import com.isc.mapper.GenderMapper;
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
	public ResponseDto<List<EmployeeTableResponseDTO>> getAllTable() {
		List<EmployeeTableResponseDTO> response = employeeRepository.findAll().stream()
				.map(EmployeeMapper::toTableResponse).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empleados listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<EmployeeCatalogResponseDTO>> getSimpleList() {
		List<EmployeeCatalogResponseDTO> response = employeeRepository.findAll().stream()
				.map(EmployeeMapper::toCatalogResponse).collect(Collectors.toList());
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
	public ResponseDto<EmployeeDetailResponseDTO> save(EmployeeRequestDTO request) {
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
		EmployeeDetailResponseDTO response = EmployeeMapper.toDetailResponse(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<EmployeeDetailResponseDTO> update(EmployeeRequestDTO request, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
