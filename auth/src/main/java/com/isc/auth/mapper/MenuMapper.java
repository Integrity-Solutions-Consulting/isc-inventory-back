package com.isc.auth.mapper;

import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.entitys.MenuEntity;

public class MenuMapper {
	 public static MenuResponseDTO toDto(MenuEntity entity) {
	        if (entity == null) return null;
	        MenuResponseDTO menu = new MenuResponseDTO();
	        menu.setId(entity.getId());
	        menu.setLabel(entity.getLabel());
	        menu.setDescription(entity.getDescription());
	        menu.setRoute(entity.getRoute());
	        menu.setParentId(entity.getParentId());
	        menu.setActive(entity.getActive());
	        menu.setIcon(entity.getIcon());
	        menu.setOrder(entity.getOrder());
	        menu.setCreationDate(entity.getCreationDate());
	        menu.setLastModificationDate(entity.getLastModificationDate());
	        menu.setInternalRoute(entity.getInternalRoute());
	        menu.setApplicationId(entity.getApplicationId());
	        return menu;
	    }
}
