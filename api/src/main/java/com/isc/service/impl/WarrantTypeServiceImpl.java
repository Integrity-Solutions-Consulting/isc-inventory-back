package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.WarrantTypeRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentEntity;
import com.isc.entitys.WarrantTypeEntity;
import com.isc.mapper.WarrantTypeMapper;
import com.isc.repository.EquipmentRepository;
import com.isc.repository.WarrantTypeRepository;
import com.isc.service.WarrantTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarrantTypeServiceImpl implements WarrantTypeService {

    private final WarrantTypeRepository warrantTypeRepository;
    private final EquipmentRepository equipmentRepository;


    @Override
    public WarrantTypeEntity save(WarrantTypeRequestDTO request) {
        WarrantTypeEntity entity = new WarrantTypeEntity();
        entity.setConditions(request.getConditions());
        entity.setWarrantyStartDate(request.getWarrantyStartDate());
        entity.setWarrantyEndDate(request.getWarrantyEndDate());
        entity.setSupportContact(request.getSupportContact());

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
}
