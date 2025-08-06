package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentDismissalTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.entitys.EquipmentDismissalTypeEntity;
import com.isc.api.mapper.EquipmentDismissalTypeMapper;
import com.isc.api.repository.EquipmentDismissalTypeRepository;
import com.isc.api.service.EquipmentDismissalTypeService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentDismissalTypeServiceImpl implements EquipmentDismissalTypeService {

    private final EquipmentDismissalTypeRepository dismissalTypeRepository;

    @Override
    public ResponseDto<List<EquipmentDismissalTypeResponseDTO>> getAllActive() {
        List<EquipmentDismissalTypeResponseDTO> response = dismissalTypeRepository.findAllByStatusTrue()
                .stream()
                .map(EquipmentDismissalTypeMapper::toSimpleDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de baja listados correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<EquipmentDismissalTypeResponseDTO> saveDismissalType(EquipmentDismissalTypeRequestDTO request) {
        EquipmentDismissalTypeEntity entity = new EquipmentDismissalTypeEntity();
        entity.setName(request.getName());
        entity.setStatus(true);
        entity.setCreationDate(LocalDateTime.now());

        EquipmentDismissalTypeEntity saved = dismissalTypeRepository.save(entity);
        EquipmentDismissalTypeResponseDTO response = EquipmentDismissalTypeMapper.toSimpleDto(saved);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Tipo de baja registrado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<EquipmentDismissalTypeResponseDTO> updateDismissalType(EquipmentDismissalTypeRequestDTO request, Integer id) {
        EquipmentDismissalTypeEntity entity = dismissalTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de baja no encontrado"));

        entity.setName(request.getName());
        entity.setModificationDate(LocalDateTime.now());

        EquipmentDismissalTypeEntity updated = dismissalTypeRepository.save(entity);
        EquipmentDismissalTypeResponseDTO response = EquipmentDismissalTypeMapper.toSimpleDto(updated);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de baja actualizado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<MessageResponseDTO> inactiveDismissalType(Integer id) {
        int rowsAffected = dismissalTypeRepository.deactivate(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar el tipo de baja con ID: " + id);
        }

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de baja inactivado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Transactional
    @Override
    public ResponseDto<MessageResponseDTO> activeDismissalType(Integer id) {
        int rowsAffected = dismissalTypeRepository.activate(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar el tipo de baja con ID: " + id);
        }

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de baja activado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
