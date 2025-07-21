package com.isc.api.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WarrantTypeDetailResponseDTO;

import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.WarrantTypeEntity;
import com.isc.api.mapper.WarrantTypeMapper;
import com.isc.api.repository.WarrantTypeRepository;
import com.isc.api.service.WarrantTypeService;
import com.isc.api.entitys.EquipmentEntity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarrantTypeServiceImpl implements WarrantTypeService {

    private final WarrantTypeRepository warrantTypeRepository;

    @Override
    public WarrantTypeEntity save(WarrantTypeRequestDTO request, EquipmentEntity equipment) {
        WarrantTypeEntity entity = new WarrantTypeEntity();
        entity.setConditions(request.getConditions());
        entity.setWarrantyStartDate(request.getWarrantyStartDate());
        entity.setWarrantyEndDate(request.getWarrantyEndDate());
        entity.setSupportContact(request.getSupportContact());
        entity.setEquipment(equipment);

        return entity;
    }

    @Override
    public WarrantTypeEntity update(WarrantTypeRequestDTO request, Integer idWarranty) {
        WarrantTypeEntity entity = warrantTypeRepository.findById(idWarranty)
                .orElseThrow(() -> new RuntimeException("Garantía no encontrada"));
        
        

        entity.setConditions(request.getConditions());
        entity.setWarrantyStartDate(request.getWarrantyStartDate());
        entity.setWarrantyEndDate(request.getWarrantyEndDate());
        entity.setSupportContact(request.getSupportContact());


        return entity;
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = warrantTypeRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la garantía con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Garantía inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = warrantTypeRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la garantía con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Garantía activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
    
    
    @Override
    public ResponseDto<WarrantTypeDetailResponseDTO> findById(Integer id) {
        Optional<WarrantTypeEntity> warranties = warrantTypeRepository.findByIdAndStatusTrue(id);

        if (warranties.isEmpty()) {
            throw new RuntimeException("No se encontró una garantía activa con el ID: " + id);
        }

        WarrantTypeDetailResponseDTO dto = WarrantTypeMapper.toDetailResponseDTO(warranties.get());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Garantía encontrada correctamente");

        return new ResponseDto<>(dto, metadata);
    }

}
