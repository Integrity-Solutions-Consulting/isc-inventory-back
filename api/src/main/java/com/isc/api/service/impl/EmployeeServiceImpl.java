package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.dto.request.EmployeeRequestDTO;
import com.isc.api.dto.response.CustomerDetailResponseDTO;
import com.isc.api.dto.response.EmployeeCatalogResponseDTO;
import com.isc.api.dto.response.EmployeeDetailResponseDTO;
import com.isc.api.dto.response.EmployeeTableResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.CustomerEntity;
import com.isc.api.entitys.EmployeeEntity;
import com.isc.api.entitys.GenderEntity;
import com.isc.api.entitys.IdentificationTypeEntity;
import com.isc.api.entitys.NationalityEntity;
import com.isc.api.entitys.PositionEntity;
import com.isc.api.entitys.WorkModeEntity;
import com.isc.api.mapper.CustomerMapper;
import com.isc.api.mapper.EmployeeMapper;
import com.isc.api.repository.EmployeeRepository;
import com.isc.api.repository.GenderRepository;
import com.isc.api.repository.IdentificationTypeRepository;
import com.isc.api.repository.NationalityRepository;
import com.isc.api.repository.PositionRepository;
import com.isc.api.repository.WorkModeRepository;
import com.isc.api.service.EmployeeService;

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
    public ResponseDto<EmployeeDetailResponseDTO> findByIdentification(String identification) 
	{
        EmployeeEntity entity = employeeRepository.findByIdentification(identification)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con RUC: " + identification));
        
        return new ResponseDto<>(EmployeeMapper.toDetailResponse(entity), 
                new MetadataResponseDto(HttpStatus.OK, "Cliente encontrado"));
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

	@Transactional
	@Override
	public ResponseDto<EmployeeTableResponseDTO> save(EmployeeRequestDTO request) {
	    employeeRepository.findByIdentification(request.getIdentification())
	        .ifPresent(employee -> {
	            throw new RuntimeException("Ya existe un empleado con esta identificación: " + request.getIdentification());
	        });

	    IdentificationTypeEntity identificationType = identificationTypeRepository
	            .findById(request.getIdIdentificationType())
	            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrado"));
	    
	    GenderEntity gender = genderRepository.findById(request.getIdGender())
	            .orElseThrow(() -> new RuntimeException("Género no encontrado"));
	    
	    NationalityEntity nationality = nationalityRepository
	            .findById(request.getIdNationality())
	            .orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada"));
	    
	    PositionEntity position = positionRepository
	            .findById(request.getIdPosition())
	            .orElseThrow(() -> new RuntimeException("Posición no encontrada"));
	    
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
	    MetadataResponseDto metadata = new MetadataResponseDto(
	        HttpStatus.OK,
	        "Empleado registrado correctamente"
	    );
	    return new ResponseDto<>(response, metadata);
	}
	
	@Transactional
	@Override
	public ResponseDto<EmployeeTableResponseDTO> update(EmployeeRequestDTO request, String identification) {
		
		 EmployeeEntity entity = employeeRepository.findByIdentification(identification)
		            .orElseThrow(() -> new RuntimeException("Empleado no encontrado con identificación: " + identification));

		    // 2. Validar si la nueva identificación (si cambió) ya existe en otro empleado
		    if (!identification.equals(request.getIdentification())) {
		        employeeRepository.findByIdentification(request.getIdentification())
		            .ifPresent(existingEmployee -> {
		                throw new RuntimeException("Ya existe otro empleado con esta identificación: " + request.getIdentification());
		            });
		    }

	    if (!entity.getIdentificationType().getId().equals(request.getIdIdentificationType())) {
	        IdentificationTypeEntity identificationType = identificationTypeRepository
	                .findById(request.getIdIdentificationType())
	                .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
	        entity.setIdentificationType(identificationType);
	    }
	    
	    if (!entity.getGender().getId().equals(request.getIdGender())) {
	        GenderEntity gender = genderRepository.findById(request.getIdGender())
	                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
	        entity.setGender(gender);
	    }
	    
	    if (!entity.getNationality().getId().equals(request.getIdNationality())) {
	        NationalityEntity nationality = nationalityRepository
	                .findById(request.getIdNationality())
	                .orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada"));
	        entity.setNationality(nationality);
	    }
	    
	    if (!entity.getPosition().getId().equals(request.getIdPosition())) {
	        PositionEntity position = positionRepository
	                .findById(request.getIdPosition())
	                .orElseThrow(() -> new RuntimeException("Posición no encontrada"));
	        entity.setPosition(position);
	    }
	    
	    if (!entity.getWorkMode().getId().equals(request.getIdWorkMode())) {
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
	            "Información del empleado actualizada correctamente");
	    
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