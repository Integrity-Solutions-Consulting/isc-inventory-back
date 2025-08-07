package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentDismissalRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.entitys.EquipmentDismissalEntity;
import com.isc.api.entitys.EquipmentDismissalTypeEntity;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.mapper.EquipmentDismissalMapper;
import com.isc.api.repository.EquipmentDismissalRepository;
import com.isc.api.repository.EquipmentDismissalTypeRepository;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.service.EquipmentDismissalService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentDismissalServiceImpl implements EquipmentDismissalService {

    private final EquipmentDismissalRepository dismissalRepository;
    private final EquipmentDismissalTypeRepository dismissalTypeRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    public ResponseDto<List<EquipmentDismissalResponseDTO>> getAllActive() {
        List<EquipmentDismissalResponseDTO> response = dismissalRepository.findAll()
                .stream()
                .map(EquipmentDismissalMapper::toSimpleDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Bajas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<EquipmentDismissalResponseDTO> saveDismissal(EquipmentDismissalRequestDTO request) {
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        EquipmentDismissalTypeEntity dismissalType = dismissalTypeRepository.findByIdAndStatusTrue(request.getDismissalTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de baja no encontrado"));

        EquipmentDismissalEntity entity = new EquipmentDismissalEntity();
        entity.setEquipment(equipment);
        entity.setDismissalType(dismissalType);
        entity.setStatus(true);
        entity.setCreationDate(LocalDateTime.now());

        EquipmentDismissalEntity saved = dismissalRepository.save(entity);

        EquipmentDismissalResponseDTO response = EquipmentDismissalMapper.toSimpleDto(saved);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Baja registrada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<EquipmentDismissalResponseDTO> updateDismissal(EquipmentDismissalRequestDTO request, Integer id) {
        EquipmentDismissalEntity entity = dismissalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Baja no encontrada"));

        EquipmentDismissalTypeEntity dismissalType = dismissalTypeRepository.findByIdAndStatusTrue(request.getDismissalTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de baja no encontrado"));

        entity.setDismissalType(dismissalType);
        entity.setModificationDate(LocalDateTime.now());

        EquipmentDismissalEntity updated = dismissalRepository.save(entity);
        EquipmentDismissalResponseDTO response = EquipmentDismissalMapper.toSimpleDto(updated);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Baja actualizada correctamente");
        return new ResponseDto<>(response, metadata);
    }
    
    @Override
    public ResponseDto<MessageResponseDTO> activeDismissal(Integer id) 
    {
        int rowsAffected = dismissalRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la garantía con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Garantía activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
    
    @Override
    public ResponseDto<MessageResponseDTO> inactiveDismissal(Integer id) {
        int rowsAffected = dismissalRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la garantía con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Garantía inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
