package com.isc.mapper;

import org.springframework.stereotype.Component;
import com.isc.dto.response.CompanyDetailResponseDTO;
import com.isc.dto.response.CompanyResponseDTO;
import com.isc.entitys.CompanyEntity;

@Component
public class CompanyMapper {

    public CompanyResponseDTO toResponseDTO(CompanyEntity entity) {
        return new CompanyResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getTaxId(),
            entity.getEmail(),
            entity.getStatus()
        );
    }

    public CompanyDetailResponseDTO toDetailDTO(CompanyEntity entity) {
        return new CompanyDetailResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getTaxId(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getStatus(),
            entity.getCreationUser(),
            entity.getModificationUser(),
            entity.getCreationDate(),
            entity.getModificationDate(),
            entity.getCreationIp(),
            entity.getModificationIp()
        );
    }
}
