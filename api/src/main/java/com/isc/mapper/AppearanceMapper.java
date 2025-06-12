package com.isc.mapper;

import com.isc.dto.request.AppearanceRequestDTO;
import com.isc.dto.response.AppearanceDetailResponseDTO;
import com.isc.dto.response.AppearanceResponseDTO;
import com.isc.entitys.AppearanceEntity;

import java.time.LocalDateTime;

public class AppearanceMapper {

    public static AppearanceEntity toEntity(AppearanceRequestDTO dto) {
        AppearanceEntity entity = new AppearanceEntity();
        entity.setLogin_background(dto.getLogin_background());
        entity.setTypography(dto.getTypography());
        entity.setFix_header(dto.getFix_header());
        entity.setMenu_position(dto.getMenu_position());
        entity.setCollapsed_menu(dto.getCollapsed_menu());
        entity.setBackground_color(dto.getBackground_color());
        entity.setBox_order(dto.getBox_order());
        entity.setBox_background(dto.getBox_background());
        return entity;
    }

    public static AppearanceDetailResponseDTO toDetailDto(AppearanceEntity entity) {
        AppearanceDetailResponseDTO dto = new AppearanceDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setLogin_background(entity.getLogin_background());
        dto.setTypography(entity.getTypography());
        dto.setFix_header(entity.getFix_header());
        dto.setMenu_position(entity.getMenu_position());
        dto.setCollapsed_menu(entity.getCollapsed_menu());
        dto.setBackground_color(entity.getBackground_color());
        dto.setBox_order(entity.getBox_order());
        dto.setBox_background(entity.getBox_background());
        dto.setStatus(entity.getActive());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }

    public static AppearanceResponseDTO toSimpleDto(AppearanceEntity entity) {
        AppearanceResponseDTO dto = new AppearanceResponseDTO();
        dto.setId(entity.getId());
        dto.setLogin_background(entity.getLogin_background());
        dto.setTypography(entity.getTypography());
        dto.setFix_header(entity.getFix_header());
        dto.setMenu_position(entity.getMenu_position());
        dto.setCollapsed_menu(entity.getCollapsed_menu());
        dto.setBackground_color(entity.getBackground_color());
        dto.setBox_order(entity.getBox_order());
        dto.setBox_background(entity.getBox_background());
        return dto;
    }
}
