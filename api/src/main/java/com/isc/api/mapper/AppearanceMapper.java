package com.isc.api.mapper;

import java.time.LocalDateTime;
import com.isc.api.dto.request.AppearanceRequestDTO;
import com.isc.api.dto.response.AppearanceDetailResponseDTO;
import com.isc.api.dto.response.AppearanceResponseDTO;
import com.isc.api.entitys.AppearanceEntity;

public class AppearanceMapper {

    public static AppearanceEntity toEntity(AppearanceRequestDTO dto) {
        AppearanceEntity entity = new AppearanceEntity();
        entity.setLoginBackground(dto.getLogin_background());
        entity.setTypography(dto.getTypography());
        entity.setFixedHeader(dto.getFixed_header());
        entity.setMenuPosition(dto.getMenu_position());
        entity.setCollapsedMenu(dto.getCollapsed_menu());
        entity.setBackgroundColor(dto.getBackground_color());
        entity.setBoxBorder(dto.getBox_border());
        entity.setBoxBackground(dto.getBox_background());
        entity.setActive(true); // Por defecto al crear
        entity.setCreationDate(LocalDateTime.now());
        return entity;
    }

    public static AppearanceDetailResponseDTO toDetailDTO(AppearanceEntity entity) {
        return new AppearanceDetailResponseDTO(
            entity.getId(),
            entity.getLoginBackground(),
            entity.getTypography(),
            entity.getFixedHeader(),
            entity.getMenuPosition(),
            entity.getCollapsedMenu(),
            entity.getBackgroundColor(),
            entity.getBoxBorder(),
            entity.getBoxBackground(),
            entity.getActive(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }

    public static AppearanceResponseDTO toSimpleDTO(AppearanceEntity entity) {
        return new AppearanceResponseDTO(
            entity.getId(),
            entity.getLoginBackground(),
            entity.getTypography(),
            entity.getFixedHeader(),
            entity.getMenuPosition(),
            entity.getCollapsedMenu(),
            entity.getBackgroundColor(),
            entity.getBoxBorder(),
            entity.getBoxBackground()
        );
    }
}
