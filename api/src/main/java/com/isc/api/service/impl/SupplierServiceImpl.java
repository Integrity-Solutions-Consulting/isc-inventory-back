package com.isc.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.NationalityRequestDTO;
import com.isc.api.dto.request.SupplierRequestDTO;
import com.isc.api.dto.request.SupplierTypeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import jakarta.transaction.Transactional;

import com.isc.api.entitys.NationalityEntity;
import com.isc.api.entitys.SupplierEntity;
import com.isc.api.entitys.SupplierTypeEntity;
import com.isc.api.mapper.SupplierMapper;
import com.isc.api.repository.NationalityRepository;
import com.isc.api.repository.SupplierRepository;
import com.isc.api.repository.SupplierTypeRepository;
import com.isc.api.service.SupplierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierTypeRepository supplierTypeRepository;
    private final NationalityRepository nationalityRepository;
    
    @Override
    public ResponseDto<List<SupplierDetailResponseDTO>> getAllDetails() {
        List<SupplierDetailResponseDTO> response = supplierRepository.findAll().stream()
                .map(SupplierMapper::toDetailDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedores listados correctamente");
        return new ResponseDto<>(response, metadata);
    }
    
    @Override
    public ResponseDto<List<SupplierResponseDTO>> getBySupplierType(Integer supplierTypeId) {
        // Validar que el tipo de proveedor existe
        if (!supplierTypeRepository.existsById(supplierTypeId)) {
            throw new RuntimeException("Tipo de proveedor no encontrado con ID: " + supplierTypeId);
        }
        
        // Obtener todos los proveedores por tipo (activos e inactivos)
        List<SupplierResponseDTO> response = supplierRepository.findBySupplierTypeId(supplierTypeId)
                .stream()
                .map(SupplierMapper::toSimpleDto)
                .collect(Collectors.toList());
        
        MetadataResponseDto metadata = new MetadataResponseDto(
            HttpStatus.OK, 
            "Proveedores del tipo " + supplierTypeId + " listados correctamente"
        );
        
        return new ResponseDto<>(response, metadata);
    }
    

    @Override
    public ResponseDto<List<SupplierResponseDTO>> getSimpleList() {
        List<SupplierResponseDTO> response = supplierRepository.findAllByStatusTrue().stream()
                .map(SupplierMapper::toSimpleDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedores activos listados correctamente");
        return new ResponseDto<>(response, metadata);
    }
    
    @Transactional
    @Override
    public ResponseDto<SupplierDetailResponseDTO> save(SupplierRequestDTO request) {
    	if (request.getSupplierType() == null || request.getSupplierType() == null) {
            throw new RuntimeException("El tipo de proveedor es obligatorio");
        }
    	
    	Integer supplierTypeId = request.getSupplierType().getId();
    	Integer nationalityId=request.getNationality().getId();

    	
    	SupplierTypeEntity supplierType = supplierTypeRepository.findById(supplierTypeId)
    	        .orElseThrow(() -> new RuntimeException("Tipo proveedor no encontrado"));

    	NationalityEntity nationality= nationalityRepository.findById(nationalityId)
    			.orElseThrow(() -> new RuntimeException("Tipo proveedor no encontrado"));
    	
        SupplierEntity entity = new SupplierEntity();
        entity.setBusinessName(request.getBusinessName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setRuc(request.getRuc());
        entity.setEmail(request.getEmail());
        entity.setSupplierType(supplierType);
        entity.setNationality(nationality);

        entity = supplierRepository.save(entity);
        
        SupplierDetailResponseDTO response = SupplierMapper.toDetailDto(entity);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedor registrado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Transactional
    @Override
    public ResponseDto<SupplierDetailResponseDTO> update(SupplierRequestDTO request, Integer id) {
        SupplierEntity entity = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        entity.setBusinessName(request.getBusinessName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setRuc(request.getRuc());
        
        SupplierTypeRequestDTO supplierTypeDto = request.getSupplierType();
        NationalityRequestDTO nationalityDto = request.getNationality();
        
        SupplierTypeEntity supplierTypeEntity = new SupplierTypeEntity();
        NationalityEntity nationalityEntity = new NationalityEntity();
        
        supplierTypeEntity.setId(supplierTypeDto.getId());
        nationalityEntity.setId(nationalityDto.getId());

        entity.setSupplierType(supplierTypeEntity);
        entity.setNationality(nationalityEntity);

        entity = supplierRepository.save(entity);
        SupplierDetailResponseDTO response = SupplierMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedor actualizado correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = supplierRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar el proveedor con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedor inactivado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = supplierRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar el proveedor con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Proveedor activado correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
