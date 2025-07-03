package com.isc.auth.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.entitys.MenuEntity;

public class MenuMapper {
	public static MenuResponseDTO toDto(MenuEntity entity) {
		if (entity == null)
			return null;
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
	
	public static List<MenuResponseDTO> toDtoTree(List<MenuEntity> entities) {
	    if (entities == null || entities.isEmpty()) return new ArrayList<>();

	    // Primero convertir todos los menus a DTOs
	    List<MenuResponseDTO> dtoList = entities.stream()
	            .map(MenuMapper::toDto)
	            .collect(Collectors.toList());
	    

	    // Crear un mapa para acceso rápido por ID
	    Map<Integer, MenuResponseDTO> dtoMap = dtoList.stream()
	            .collect(Collectors.toMap(MenuResponseDTO::getId, dto -> dto));

	    // Construir jerarquía
	    List<MenuResponseDTO> roots = new ArrayList<>();
	    for (MenuResponseDTO dto : dtoList) {
	        if (dto.getParentId() == null || dto.getParentId() == 0) {
	            roots.add(dto);
	        } else {
	            MenuResponseDTO parent = dtoMap.get(dto.getParentId());
	            if (parent != null) {
	                if (parent.getChildren() == null) {
	                    parent.setChildren(new ArrayList<>());
	                }
	                parent.getChildren().add(dto);
	            }
	        }
	    }

	    return roots;
	}
	
	public static Set<MenuResponseDTO> buildMenuTree(Set<MenuResponseDTO> flatMenus) {

	    Map<Integer, MenuResponseDTO> dtoMap = flatMenus.stream()
	            .collect(Collectors.toMap(MenuResponseDTO::getId, dto -> dto));

	    // Construir jerarquía
	    Set<MenuResponseDTO> roots = new HashSet<>();
	    for (MenuResponseDTO dto : flatMenus) {
	        if (dto.getParentId() == null || dto.getParentId() == 0) {
	            roots.add(dto);
	        } else {
	            MenuResponseDTO parent = dtoMap.get(dto.getParentId());
	            if (parent != null) {
	                if (parent.getChildren() == null) {
	                    parent.setChildren(new ArrayList<>());
	                }
	                parent.getChildren().add(dto);
	            }
	        }
	    }
	    System.out.println(roots);
	    return roots;
	}
}
