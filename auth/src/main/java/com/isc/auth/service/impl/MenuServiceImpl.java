package com.isc.auth.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.auth.dto.request.MenuRequestDTO;
import com.isc.auth.dto.response.ApplicationResponseDTO;
import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.entitys.MenuEntity;
import com.isc.auth.mapper.ApplicationMapper;
import com.isc.auth.mapper.MenuMapper;
import com.isc.auth.repository.ApplicationRepository;
import com.isc.auth.repository.MenuRepository;
import com.isc.auth.service.MenuService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;
	
	@Override
	public ResponseDto<List<MenuResponseDTO>> getAll() {
		List<MenuEntity> menus = menuRepository.findAllByActiveTrueOrderByOrderAsc();
	    List<MenuResponseDTO> dtoList = menus.stream()
	            .map(MenuMapper::toDto)
	            .collect(Collectors.toList());
	    
	    //Construimos la jerarquia de los menus
	    Map<Integer, MenuResponseDTO> dtoMap = dtoList.stream()
	            .collect(Collectors.toMap(MenuResponseDTO::getId, dto -> dto));

	    List<MenuResponseDTO> roots = new ArrayList<>();
	    
	    for (MenuResponseDTO dto : dtoList) {
	        if (dto.getParentId() == null) {
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
	    
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Menus listados correctamente");

		return new ResponseDto<>(roots, metadata);
	}

	@Override
	public ResponseDto<MenuResponseDTO> save(MenuRequestDTO request) {
		MenuEntity menu = new MenuEntity();
		menu.setLabel(request.getLabel());
	    menu.setDescription(request.getDescription());
	    menu.setRoute(request.getRoute());
	    menu.setInternalRoute(request.getInternalRoute() != null ? request.getInternalRoute() : true);
	    menu.setApplicationId(request.getApplicationId());
	    menu.setParentId(request.getParentId());
	    menu.setIcon(request.getIcon());
	    menu.setOrder(request.getOrder());
		menu = menuRepository.save(menu);
		MenuResponseDTO responseDTO = MenuMapper.toDto(menu);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Aplicacion guardada correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<MenuResponseDTO> update(MenuRequestDTO request, Integer id) {
		MenuEntity menu = menuRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Menu no encontrado con ID: " + id));
		menu.setLabel(request.getLabel());
	    menu.setDescription(request.getDescription());
	    menu.setRoute(request.getRoute());
	    menu.setInternalRoute(request.getInternalRoute() != null ? request.getInternalRoute() : true);
	    menu.setApplicationId(request.getApplicationId());
	    menu.setParentId(request.getParentId());
	    menu.setIcon(request.getIcon());
	    menu.setOrder(request.getOrder());
	    menu.setLastModificationDate(LocalDateTime.now());
	    menu = menuRepository.save(menu);
	    MenuResponseDTO responseDTO = MenuMapper.toDto(menu);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Aplicacion guardada correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  menuRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  menuRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

}
