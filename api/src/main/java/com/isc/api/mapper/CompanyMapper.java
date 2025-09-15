package com.isc.api.mapper;

import org.springframework.stereotype.Component;
import com.isc.api.dto.response.CompanyDetailResponseDTO;
import com.isc.api.dto.response.CompanyResponseDTO;
import com.isc.api.entitys.CompanyEntity;

@Component
public class CompanyMapper {

    public CompanyResponseDTO toResponseDTO(CompanyEntity entity) {
        return new CompanyResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getRuc()
        );
    }

    public CompanyDetailResponseDTO toDetailDTO(CompanyEntity entity) {
        return new CompanyDetailResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getRuc(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
