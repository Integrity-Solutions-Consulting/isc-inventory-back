package com.isc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.EquipmentEntity;
import com.isc.entitys.EquipmentAssignmentEntity;
import com.isc.mapper.EquipmentAssignmentMapper;
import com.isc.repository.EmployeeRepository;
import com.isc.repository.EquipmentRepository;
import com.isc.repository.EquipmentAssignmentRepository;
import com.isc.service.EquipmentAssignmentService;

@Service
public class EquipmentAssignmentServiceImpl implements EquipmentAssignmentService {

    @Autowired
    private EquipmentAssignmentRepository assignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public ResponseDto<List<EquipmentAssignmentDetailResponseDTO>> getAllDetails() {
        List<EquipmentAssignmentDetailResponseDTO> response = assignmentRepository.findAll().stream()
                .map(EquipmentAssignmentMapper::toDetailDTO)
                .collect(Collectors.toList());

        MetadataResponseDto meta = new MetadataResponseDto(HttpStatus.OK, "Asignaciones detalladas listadas correctamente");
        return new ResponseDto<>(response, meta);
    }

    @Override
    public ResponseDto<List<EquipmentAssignmentResponseDTO>> getSimpleList() {
        List<EquipmentAssignmentResponseDTO> response = assignmentRepository.findAll().stream()
                .map(EquipmentAssignmentMapper::toResponseDTO)
                .collect(Collectors.toList());

        MetadataResponseDto meta = new MetadataResponseDto(HttpStatus.OK, "Asignaciones listadas correctamente");
        return new ResponseDto<>(response, meta);
    }

    @Override
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> save(EquipmentAssignmentRequestDTO request) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(request.getEmployee());
        Optional<EquipmentEntity> equipmentOpt = equipmentRepository.findById(request.getEquipment());

        if (employeeOpt.isEmpty() || equipmentOpt.isEmpty()) 
        {
        	throw new RuntimeException("Tipo de identificacion no encontrada");        
        }

        EquipmentAssignmentEntity entity = new EquipmentAssignmentEntity();
        entity.setEmployee(employeeOpt.get());
        entity.setEquipment(equipmentOpt.get());
        entity.setAssignmentDate(request.getAssigmentDate());
        entity.setStatus(true);

        EquipmentAssignmentEntity saved = assignmentRepository.save(entity);
        EquipmentAssignmentDetailResponseDTO dto = EquipmentAssignmentMapper.toDetailDTO(saved);

        return new ResponseDto<>(dto, new MetadataResponseDto(HttpStatus.CREATED, "Asignación registrada correctamente"));
    }

    @Override
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> update(EquipmentAssignmentRequestDTO request, Integer id) {
        Optional<EquipmentAssignmentEntity> existingOpt = assignmentRepository.findById(id);
        if (existingOpt.isEmpty()) {
        	throw new RuntimeException("Tipo de identificacion no encontrada");
        }

        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(request.getEmployee());
        Optional<EquipmentEntity> equipmentOpt = equipmentRepository.findById(request.getEquipment());

        if (employeeOpt.isEmpty() || equipmentOpt.isEmpty()) {
            return new ResponseDto<>(null, new MetadataResponseDto(HttpStatus.NOT_FOUND, "Empleado o equipo no encontrado"));
        }

        EquipmentAssignmentEntity entity = existingOpt.get();
        entity.setEmployee(employeeOpt.get());
        entity.setEquipment(equipmentOpt.get());
        entity.setAssignmentDate(request.getAssigmentDate());

        EquipmentAssignmentEntity updated = assignmentRepository.save(entity);
        EquipmentAssignmentDetailResponseDTO dto = EquipmentAssignmentMapper.toDetailDTO(updated);

        return new ResponseDto<>(dto, new MetadataResponseDto(HttpStatus.OK, "Asignación actualizada correctamente"));
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        Optional<EquipmentAssignmentEntity> entityOpt = assignmentRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return new ResponseDto<>(null, new MetadataResponseDto(HttpStatus.NOT_FOUND, "Asignación no encontrada"));
        }

        EquipmentAssignmentEntity entity = entityOpt.get();
        entity.setStatus(false);
        assignmentRepository.save(entity);

        return new ResponseDto<>(new MessageResponseDTO("Asignación desactivada correctamente"),
                new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        Optional<EquipmentAssignmentEntity> entityOpt = assignmentRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return new ResponseDto<>(null, new MetadataResponseDto(HttpStatus.NOT_FOUND, "Asignación no encontrada"));
        }

        EquipmentAssignmentEntity entity = entityOpt.get();
        entity.setStatus(true);
        assignmentRepository.save(entity);

        return new ResponseDto<>(new MessageResponseDTO("Asignación activada correctamente"),
                new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }
}
