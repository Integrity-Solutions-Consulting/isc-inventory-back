package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.EquipmentAssignmentEntity;
import com.isc.entitys.EquipmentEntity;
import com.isc.mapper.EquipmentAssignmentMapper;
import com.isc.repository.EmployeeRepository;
import com.isc.repository.EquipmentAssignmentRepository;
import com.isc.repository.EquipmentRepository;
import com.isc.service.EquipmentAssignmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentAssignmentServiceImpl implements EquipmentAssignmentService {

    private final EquipmentAssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails() {
        List<EquipmentAssignmentDetailResponseDTO> response = assignmentRepository.findAll().stream()
                .map(EquipmentAssignmentMapper::toDetailDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Asignaciones listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList() {
        List<EquipmentAssignmentResponseDTO> response = assignmentRepository.findAllByStatusTrue().stream()
                .map(EquipmentAssignmentMapper::toResponseDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Asignaciones activas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> save(EquipmentAssignmentRequestDTO request) {
        EmployeeEntity employee = employeeRepository.findById(request.getEmployee())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        EquipmentAssignmentEntity entity = EquipmentAssignmentMapper.toEntity(request, employee, equipment);
        entity = assignmentRepository.save(entity);

        EquipmentAssignmentDetailResponseDTO response = EquipmentAssignmentMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Asignación creada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> update(EquipmentAssignmentRequestDTO request, Integer id) {
        EquipmentAssignmentEntity entity = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        EmployeeEntity employee = employeeRepository.findById(request.getEmployee())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        entity.setEmployee(employee);
        entity.setEquipment(equipment);
        entity.setAssigmentDate(request.getAssigmentDate());
        entity.setReturnDate(request.getReturnDate());
        entity.setModificationDate(java.time.LocalDateTime.now());

        entity = assignmentRepository.save(entity);

        EquipmentAssignmentDetailResponseDTO response = EquipmentAssignmentMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Asignación actualizada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = assignmentRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la asignación con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Asignación inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = assignmentRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la asignación con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Asignación activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
