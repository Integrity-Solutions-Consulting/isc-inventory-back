package com.isc.mapper;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.entitys.ComponentTypeEntity;

import java.time.LocalDateTime;

public class ComponentTypeServiceMapper {

    public static ComponentTypeEntity toEntity(ComponentTypeRequestDTO dto, String user, String ip) {
        ComponentTypeEntity entity = new ComponentTypeEntity();
        entity.setDescription(dto.getDescription());
        entity.setStatus(true);
        entity.setCreationDate(LocalDateTime.now());
        entity.setCreationUser(user);
        entity.setCreationIp(ip);
        return entity;
    }

    public static void updateEntity(ComponentTypeEntity entity, ComponentTypeRequestDTO dto, String user, String ip) {
        entity.setDescription(dto.getDescription());
        entity.setModificationDate(LocalDateTime.now());
        entity.setModificationUser(user);
        entity.setModificationIp(ip);
    }

    public static ComponentTypeDetailResponseDTO toDetailResponseDTO(ComponentTypeEntity entity) {
        ComponentTypeDetailResponseDTO dto = new ComponentTypeDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }

    public static ComponentTypeResponseDTO toSimpleResponseDTO(ComponentTypeEntity entity) {
        ComponentTypeResponseDTO dto = new ComponentTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
