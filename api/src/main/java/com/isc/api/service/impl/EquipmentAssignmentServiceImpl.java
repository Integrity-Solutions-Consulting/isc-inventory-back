package com.isc.api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.api.dto.request.EquipmentRevokeRequestDTO;
import com.isc.api.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.EmployeeEntity;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentStatusEntity;
import com.isc.api.entitys.EquipmentAssignmentEntity;
import com.isc.api.entitys.EquipmentCategoryStockEntity;
import com.isc.api.mapper.EquipmentAssignmentMapper;
import com.isc.api.repository.EmployeeRepository;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.repository.EquipmentAssignmentRepository;
import com.isc.api.repository.EquipmentCategoryStockRepository;
import com.isc.api.service.EquipmentAssignmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentAssignmentServiceImpl implements EquipmentAssignmentService {


    private final EquipmentAssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentCategoryStockRepository categoryStockRepository;
    
    private final Integer idAvailable = 1;
    private final Integer idAssigned = 2;
    private final Integer outOfService= 7;

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
    @Transactional
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> assign(EquipmentAssignmentRequestDTO request) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(request.getEmployee());
        Optional<EquipmentEntity> equipmentOpt = equipmentRepository.findById(request.getEquipment());

        if (employeeOpt.isEmpty() || equipmentOpt.isEmpty()) 
        {
        	throw new RuntimeException("No fue posible realizar la asignación");        
        }
        
        EquipmentEntity equipment = equipmentOpt.get();
        if(equipment.getEquipStatus().getId()==this.idAssigned && equipment.getStatus()==true && equipment.getCondition().getId()!=this.outOfService) {
        	throw new RuntimeException("No fue posible realizar la asignación");   
        }
        EquipmentStatusEntity statusAsignado = new EquipmentStatusEntity();
        statusAsignado.setId(this.idAssigned);
        equipment.setEquipStatus(statusAsignado);
        equipment = equipmentRepository.save(equipment);
        EquipmentCategoryStockEntity stock = equipment.getCategory().getStock();
        stock.setStock(stock.getStock()-1);
        categoryStockRepository.save(stock);
        
        EquipmentAssignmentEntity entity = new EquipmentAssignmentEntity();
        entity.setEmployee(employeeOpt.get());
        entity.setEquipment(equipment);
        entity.setAssignmentDate(request.getAssigmentDate());

        EquipmentAssignmentEntity saved = assignmentRepository.save(entity);
        EquipmentAssignmentDetailResponseDTO dto = EquipmentAssignmentMapper.toDetailDTO(saved);

        return new ResponseDto<>(dto, new MetadataResponseDto(HttpStatus.CREATED, "Asignación registrada correctamente"));
    }

    @Override
    @Transactional
    public ResponseDto<EquipmentAssignmentDetailResponseDTO> revoke(Integer id, EquipmentRevokeRequestDTO request) 
    {
        Optional<EquipmentAssignmentEntity> existingOpt = assignmentRepository.findById(id);
        if (existingOpt.isEmpty()) {
        	throw new RuntimeException("Asignacion no encontrada");
        }
        EquipmentAssignmentEntity assignment = existingOpt.get();
        LocalDate returnDate = request != null ? request.getRevokeDate() : null;
        
        if (returnDate != null && returnDate.isBefore(LocalDate.now())) 
        {
            throw new IllegalArgumentException("La fecha de revocación no puede ser anterior a la fecha actual");
        }
        
        assignment.setReturnDate(returnDate !=null? returnDate:LocalDate.now());
        EquipmentStatusEntity statusAvailable = new EquipmentStatusEntity();
        statusAvailable.setId(this.idAvailable);
        EquipmentEntity equipment = assignment.getEquipment();
        equipment.setEquipStatus(statusAvailable);
        equipment = equipmentRepository.save(equipment);
        EquipmentCategoryStockEntity stock = equipment.getCategory().getStock();
        stock.setStock(stock.getStock()+1);
        categoryStockRepository.save(stock);
 
        EquipmentAssignmentEntity updated = assignmentRepository.save(assignment);
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
        if(entity.getReturnDate().equals(null)) {
        	throw new RuntimeException("Debe desasignar el equipo primero");
        }
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
