package com.isc.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.isc.api.dto.response.SupplierTypeResponseDTO;
import com.isc.api.entitys.SupplierTypeEntity;

public class SupplierTypeMapper 
{

    public static SupplierTypeResponseDTO toDto(SupplierTypeEntity entity) 
    {
        SupplierTypeResponseDTO dto = new SupplierTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    
    public static List<SupplierTypeResponseDTO> toDtoList(List<SupplierTypeEntity> entities) 
    {
        return entities.stream()
                .map(SupplierTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
