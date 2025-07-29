package com.isc.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentCategoryRequestDTO;
import com.isc.api.dto.request.EquipmentCategoryStockRequest;
import com.isc.api.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.EquipmentCategoryEntity;
import com.isc.api.mapper.EquipmentCategoryMapper;
import com.isc.api.repository.EquipmentCategoryRepository;
import com.isc.api.service.EquipmentCategoryService;
import com.isc.api.service.EquipmentCategoryStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {

    private final EquipmentCategoryRepository categoryRepository;
    private final EquipmentCategoryStockService stockService;

    @Override
    public ResponseDto<List<EquipmentCategoryDetailResponseDTO>> getAllDetails() {
        List<EquipmentCategoryDetailResponseDTO> response = categoryRepository.findAllByStatusTrue().stream()
                .map(EquipmentCategoryMapper::toDetailDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categorías listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<EquipmentCategoryResponseDTO>> getSimpleList() {
        List<EquipmentCategoryResponseDTO> response = categoryRepository.findAllByStatusTrue().stream()
                .map(EquipmentCategoryMapper::toSimpleDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categorías activas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentCategoryDetailResponseDTO> save(EquipmentCategoryRequestDTO request) {
        EquipmentCategoryEntity entity = new EquipmentCategoryEntity();
        entity.setName(request.getName());
        entity = categoryRepository.save(entity);

        EquipmentCategoryStockRequest stockRequest = new EquipmentCategoryStockRequest();
        stockRequest.setCategory(entity.getId());
        stockRequest.setStock(1); 
        stockService.save(stockRequest);
        
        EquipmentCategoryDetailResponseDTO response = EquipmentCategoryMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Categoría creada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentCategoryDetailResponseDTO> update(EquipmentCategoryRequestDTO request, Integer id) {
        EquipmentCategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        entity.setName(request.getName());
        entity.setModificationDate(java.time.LocalDateTime.now());

        entity = categoryRepository.save(entity);

        EquipmentCategoryDetailResponseDTO response = EquipmentCategoryMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoría actualizada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = categoryRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la categoría con ID: " + id);
        }
        
        stockService.inactiveByCategoryId(id);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoría inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = categoryRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la categoría con ID: " + id);
        }
        
        stockService.activeByCategoryId(id);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoría activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
