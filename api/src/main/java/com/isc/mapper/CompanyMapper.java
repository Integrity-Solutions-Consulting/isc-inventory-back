package com.isc.mapper;

import com.isc.dto.request.CompanyRequestDTO;
import com.isc.dto.response.CompanyDetailResponseDTO;
import com.isc.dto.response.CompanyResponseDTO;
import com.isc.entitys.CompanyEntity;

public class CompanyMapper {

    public static CompanyEntity toEntity(CompanyRequestDTO dto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static CompanyResponseDTO toResponseDTO(CompanyEntity entity) {
        return new CompanyResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription()
        );
    }

    public static CompanyDetailResponseDTO toDetailDTO(CompanyEntity entity) {
        return new CompanyDetailResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }

    public static void updateEntityFromDTO(CompanyEntity entity, CompanyRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}
