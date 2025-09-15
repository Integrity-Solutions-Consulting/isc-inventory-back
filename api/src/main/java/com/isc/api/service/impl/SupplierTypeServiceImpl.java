package com.isc.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.response.SupplierTypeResponseDTO;
import com.isc.api.entitys.SupplierTypeEntity;
import com.isc.api.mapper.SupplierTypeMapper;
import com.isc.api.repository.SupplierTypeRepository;
import com.isc.api.service.SupplierTypeService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierTypeServiceImpl implements SupplierTypeService {

    private final SupplierTypeRepository supplierTypeRepository;


    @Override
    public ResponseDto<List<SupplierTypeResponseDTO>> getAllActive() {
    	List<SupplierTypeResponseDTO> response= supplierTypeRepository.findAllByStatusTrue().stream()
                .map(SupplierTypeMapper::toDto)
                .collect(Collectors.toList());
    	MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedores activos listados correctamente");
        return new ResponseDto<>(response, metadata);
    }
    
    @Override
    public ResponseDto<SupplierTypeResponseDTO> getById(Integer id) {
        SupplierTypeEntity entity = supplierTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de proveedor no encontrado"));
        
        if (!entity.getStatus()) {
            throw new RuntimeException("El tipo de proveedor está inactivo");
        }
        
        SupplierTypeResponseDTO response = SupplierTypeMapper.toDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de proveedor encontrado");
        return new ResponseDto<>(response, metadata);
    }
}
