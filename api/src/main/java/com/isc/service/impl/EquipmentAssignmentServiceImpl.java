package com.isc.service.impl;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.EquipmentAssignmentEntity;
import com.isc.entitys.EquipmentEntity;
import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.mapper.EquipmentAssignmentMapper;
import com.isc.repository.EmployeeRepository;
import com.isc.repository.EquipmentAssignmentRepository;
import com.isc.repository.EquipmentRepository;
import com.isc.service.EquipmentAssignmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentAssignmentServiceImpl implements EquipmentAssignmentService{
	private final EquipmentAssignmentRepository equipmentAssignmentRepository;
	private final EmployeeRepository employeeRepository;
	private final EquipmentRepository equipmentRepository;
	
	@Override
	public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails(){
		List<EquipmentAssignmentDetailResponseDTO> response = equipmentAssignmentRepository.findAll().stream()
                .map(EquipmentAssignmentMapper::toDetailDto).collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo asignado listado correctamente");
        return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList(){
		List<EquipmentAssignmentResponseDTO> response = equipmentAssignmentRepository.findAllByStatusTrue().stream()
				.map(EquipmentAssignmentMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo asignado listado  correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> save(EquipmentAssignmentRequestDTO request){
		EmployeeEntity employee = employeeRepository.findById(request.getEmployee())
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));;
		EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));;
		EquipmentAssignmentEntity entity = new EquipmentAssignmentEntity();
		entity.setEmployee(employee);
		entity.setEquipment(equipment);
		entity.setAssignmentDate(request.getAssigmentDate());
		entity.setReturnDate(request.getReturnDate());
        entity = equipmentAssignmentRepository.save(entity);
        EquipmentAssignmentDetailResponseDTO response = EquipmentAssignmentMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo asignado guardado correctamente");
        return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentAssignmentDetailResponseDTO> update(EquipmentAssignmentRequestDTO request, Integer id){
		EquipmentAssignmentEntity entity = equipmentAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo asignadono no encontrado"));
        if (entity.getEmployee().getId()!=request.getEmployee()) {
        	EmployeeEntity employee = employeeRepository.findById(request.getEmployee())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setEmployee(employee);
		}
        if (entity.getEquipment().getId()!=request.getEquipment()) {
        	EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setEquipment(equipment);
		}
        entity.setAssignmentDate(request.getAssigmentDate());
        entity.setReturnDate(request.getReturnDate());
        entity.setModificationDate(LocalDateTime.now());
        entity = equipmentAssignmentRepository.save(entity);
        EquipmentAssignmentDetailResponseDTO response = EquipmentAssignmentMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo asignado actualizado correctamente");
        return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentAssignmentRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentAssignmentRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
}
