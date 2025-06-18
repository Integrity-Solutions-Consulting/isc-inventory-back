package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentCategoryStockRequest;
import com.isc.dto.response.EquipmentCategoryStockDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryStockResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentCategoryStockEntity;
import com.isc.mapper.EquipmentCategoryStockMapper;
import com.isc.repository.EquipmentCategoryStockRepository;
import com.isc.service.EquipmentCategoryStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryStockServiceImpl implements EquipmentCategoryStockService {

    private final EquipmentCategoryStockRepository stockRepository;

    @Override
    public ResponseDto<List<EquipmentCategoryStockDetailResponseDTO>> getAllDetails() {
        List<EquipmentCategoryStockDetailResponseDTO> response = stockRepository.findAll().stream()
                .map(EquipmentCategoryStockMapper::toDetailDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock de categorías listado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<EquipmentCategoryStockResponseDTO>> getSimpleList() {
        List<EquipmentCategoryStockResponseDTO> response = stockRepository.findAllByStatusTrue().stream()
                .map(EquipmentCategoryStockMapper::toSimpleDto)
                .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock activo listado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<EquipmentCategoryStockDetailResponseDTO> save(EquipmentCategoryStockRequest request) {
        EquipmentCategoryStockEntity entity = new EquipmentCategoryStockEntity();
        entity.setCategory(request.getCategory());
        entity.setStock(request.getStock());

        entity = stockRepository.save(entity);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Stock creado correctamente");
        return new ResponseDto<>(EquipmentCategoryStockMapper.toDetailDto(entity), metadata);
    }

    @Override
    public ResponseDto<EquipmentCategoryStockDetailResponseDTO> update(EquipmentCategoryStockRequest request, Integer id) {
        EquipmentCategoryStockEntity entity = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado"));

        entity.setCategory(request.getCategory());
        entity.setStock(request.getStock());
        entity.setModificationDate(java.time.LocalDateTime.now());

        entity = stockRepository.save(entity);

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock actualizado correctamente");
        return new ResponseDto<>(EquipmentCategoryStockMapper.toDetailDto(entity), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = stockRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar el stock con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock inactivado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = stockRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar el stock con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock activado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
