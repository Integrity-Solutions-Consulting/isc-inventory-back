package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentConditionRequestDTO;
import com.isc.dto.response.EquipmentConditionResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentConditionEntity;
import com.isc.mapper.EquipmentConditionMapper;
import com.isc.repository.EquipmentConditionRepository;
import com.isc.service.EquipmentConditionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentConditionServiceImpl implements EquipmentConditionService {

    @Autowired
    private EquipmentConditionRepository repository;

    @Override
    public ResponseDto<List<EquipmentConditionResponseDTO>> getDetails() {
        List<EquipmentConditionResponseDTO> response = repository.findAllByStatusTrue()
                .stream()
                .map(EquipmentConditionMapper::toResponseDTO)
                .collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de condiciones listadas correctamente");
		return new ResponseDto<>(response, metadata);

    }

    @Override
    public ResponseDto<EquipmentConditionResponseDTO> save(EquipmentConditionRequestDTO request) {
        EquipmentConditionEntity entity = new EquipmentConditionEntity();
        entity.setName(request.getName());
        entity.setStatus(true);
        entity.setCreationDate(java.time.LocalDateTime.now());
        EquipmentConditionEntity saved = repository.save(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de condiciones listadas correctamente");
		return new ResponseDto<>(EquipmentConditionMapper.toResponseDTO(saved), metadata);

    }
    
    @Override
    public ResponseDto<EquipmentConditionResponseDTO> update(EquipmentConditionRequestDTO request, Integer id) {
        EquipmentConditionEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condición no encontrada con ID: " + id));

        entity.setName(request.getName());
        entity.setModificationDate(java.time.LocalDateTime.now());

        EquipmentConditionEntity updated = repository.save(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de condiciones listadas correctamente");
		return new ResponseDto<>(EquipmentConditionMapper.toResponseDTO(updated), metadata);

    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int updated = repository.inactive(id);
        if (updated == 0) {
            throw new EntityNotFoundException("No se pudo desactivar la condición con ID: " + id);
        }
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de condiciones listadas correctamente");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int updated = repository.active(id);
        if (updated == 0) {
            throw new EntityNotFoundException("No se pudo activar la condición con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipos de condiciones listadas correctamente");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
    }
}
