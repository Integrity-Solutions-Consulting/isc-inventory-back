package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.ComponentTypeEntity;
import com.isc.entitys.EquipmentCharacteristicEntity;
import com.isc.mapper.EquipmentCharacteristicMapper;
import com.isc.repository.ComponentTypeRepository;
import com.isc.repository.EquipmentCharacteristicRepository;
import com.isc.service.EquipmentCharacteristicService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCharacteristicServiceImpl implements EquipmentCharacteristicService {

    private final EquipmentCharacteristicRepository characteristicRepository;
    private final ComponentTypeRepository componentRepository;

    @Override
    public ResponseDto<List<EquipmentCharacteristicDetailResponseDTO>> getAllDetails() {
        List<EquipmentCharacteristicDetailResponseDTO> response = characteristicRepository.findAll().stream()
                .map(EquipmentCharacteristicMapper::toDetailDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Características listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<EquipmentCharacteristicResponseDTO>> getSimpleList() {
        List<EquipmentCharacteristicResponseDTO> response = characteristicRepository.findAllByStatusTrue().stream()
                .map(EquipmentCharacteristicMapper::toSimpleDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Características activas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentCharacteristicDetailResponseDTO> save(EquipmentCharacteristicRequestDTO request) {
        ComponentTypeEntity component = componentRepository.findById(request.getComponent())
                .orElseThrow(() -> new RuntimeException("Componente no encontrado"));

        EquipmentCharacteristicEntity entity = new EquipmentCharacteristicEntity();
        entity.setDescription(request.getDescription());
        entity.setComponent(component);

        entity = characteristicRepository.save(entity);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Característica creada correctamente");
        return new ResponseDto<>(EquipmentCharacteristicMapper.toDetailDto(entity), metadata);
    }
    
    @Override
    public EquipmentCharacteristicEntity saveForEquipment(EquipmentCharacteristicRequestDTO request) {
        ComponentTypeEntity component = componentRepository.findById(request.getComponent())
                .orElseThrow(() -> new RuntimeException("Componente no encontrado"));

        EquipmentCharacteristicEntity entity = new EquipmentCharacteristicEntity();
        entity.setDescription(request.getDescription());
        entity.setComponent(component);
        return entity;
    }
    
    @Override
    public EquipmentCharacteristicEntity updateForEntity(EquipmentCharacteristicRequestDTO request) {
    	 EquipmentCharacteristicEntity entity = characteristicRepository.findById(request.getId())
                 .orElseThrow(() -> new RuntimeException("Característica no encontrada"));
    	 
    	 if(!request.getComponent().equals(entity.getComponent().getId())) {
    	        ComponentTypeEntity component = componentRepository.findById(request.getComponent())
    	                .orElseThrow(() -> new RuntimeException("Componente no encontrado"));
    	        entity.setComponent(component);
    	 }
        entity.setDescription(request.getDescription());
        return entity;
    }

    @Override
    public ResponseDto<EquipmentCharacteristicDetailResponseDTO> update(EquipmentCharacteristicRequestDTO request, Integer id) {
        EquipmentCharacteristicEntity entity = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica no encontrada"));

        ComponentTypeEntity component = componentRepository.findById(request.getComponent())
                .orElseThrow(() -> new RuntimeException("Componente no encontrado"));

        entity.setDescription(request.getDescription());
        entity.setComponent(component);
        entity.setModificationDate(java.time.LocalDateTime.now());

        entity = characteristicRepository.save(entity);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Característica actualizada correctamente");
        return new ResponseDto<>(EquipmentCharacteristicMapper.toDetailDto(entity), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = characteristicRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la característica con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Característica inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = characteristicRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la característica con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Característica activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
