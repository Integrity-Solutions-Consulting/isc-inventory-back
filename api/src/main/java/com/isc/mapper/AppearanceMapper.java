package com.isc.mapper;

import com.isc.dto.response.AppearanceDetailResponseDTO;
import com.isc.dto.response.AppearanceResponseDTO;
import com.isc.entitys.AppearanceEntity;

public class AppearanceMapper {
	public static AppearanceResponseDTO toSimpleDto(AppearanceEntity entity) {
		if (entity == null)
			return null;
		return new AppearanceResponseDTO(entity.getId(), entity.getLogin_background(), entity.getTypography(), entity.getFix_header(), entity.getMenu_position(), entity.getCollapsed_menu(), entity.getBackground_color(), entity.getBox_order(), entity.getBox_background());
	}
	
	public static AppearanceDetailResponseDTO toDetailDto(AppearanceEntity entity) {
		if (entity == null)
			return null;
		return new AppearanceDetailResponseDTO(entity.getId(), entity.getLogin_background(), entity.getTypography(), entity.getFix_header(), entity.getMenu_position(), entity.getCollapsed_menu(), entity.getBackground_color(), entity.getBox_order(), entity.getBox_background(), entity.getActive(), entity.getCreatedAt(), entity.getUpdatedAt());
	}
}
