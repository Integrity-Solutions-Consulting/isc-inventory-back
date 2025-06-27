package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentRepairEntity;
import com.isc.api.entitys.EquipmentStatusEntity;
import com.isc.api.mapper.EquipmentRepairMapper;
import com.isc.api.repository.EquipmentRepairRepository;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.repository.EquipmentStatusRepository;
import com.isc.api.service.EquipmentRepairService;
import org.springframework.http.HttpStatus;

@Service
public class EquipmentRepairServiceImpl implements EquipmentRepairService {

    @Autowired
    private EquipmentRepairRepository repairRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentStatusRepository statusRepository;

    @Override
    public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request) {
        // 1. Buscar el equipo
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getEquipment()));

        // 2. Buscar o crear el estado "EN_REPARACION"
        EquipmentStatusEntity repairStatus = statusRepository.findByName("EN_REPARACION")
            .orElseGet(() -> {
                EquipmentStatusEntity newStatus = new EquipmentStatusEntity();
                newStatus.setName("EN_REPARACION");
                newStatus.setStatus(true);
                newStatus.setCreationDate(LocalDateTime.now());
                return statusRepository.save(newStatus);
            });

        // 3. Asignar el estado al equipo y guardar
        equipment.setEquipStatus(repairStatus);
        equipment.setModificationDate(LocalDateTime.now());
        equipmentRepository.save(equipment);

        // 4. Crear la reparación
        EquipmentRepairEntity repair = EquipmentRepairMapper.toEntity(request, equipment);
        repair.setCreationDate(LocalDateTime.now());
        repair.setStatus(true);
        EquipmentRepairEntity savedRepair = repairRepository.save(repair);

        // 5. Convertir a DTO de respuesta
        EquipmentRepairDetailResponseDTO responseDTO = EquipmentRepairMapper.toDetailDTO(savedRepair);

        // 6. Crear metadatos y respuesta
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,"Reparación registrada correctamente");
        return new ResponseDto<>(responseDTO, metadata);
    }


    @Override
    public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails() {
        throw new UnsupportedOperationException("Método no implementado aún.");
    }

    @Override
    public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList() {
        throw new UnsupportedOperationException("Método no implementado aún.");
    }

    @Override
    public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id) {
        throw new UnsupportedOperationException("Método no implementado aún.");
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        throw new UnsupportedOperationException("Método no implementado aún.");
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        throw new UnsupportedOperationException("Método no implementado aún.");
    }
}
