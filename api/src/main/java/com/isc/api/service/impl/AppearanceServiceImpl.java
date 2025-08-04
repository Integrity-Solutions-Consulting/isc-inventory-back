
package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.isc.api.mapper.AppearanceMapper;
import com.isc.api.dto.request.AppearanceRequestDTO;
import com.isc.api.dto.response.AppearanceResponseDTO;
import com.isc.api.dto.response.AppearanceDetailResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.AppearanceEntity;
import com.isc.api.repository.AppearanceRepository;
import com.isc.api.service.AppearanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class AppearanceServiceImpl implements AppearanceService {

    private final AppearanceRepository repository;

    @Override
    public ResponseDto<List<AppearanceDetailResponseDTO>> getAllDetails() {
        List<AppearanceDetailResponseDTO> list = repository.findAll().stream()
            .map(AppearanceMapper::toDetailDTO)
            .toList();
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia listada con detalles correctamente");
        return new ResponseDto<>(list,metadata);
    }

    @Override
    public ResponseDto<List<AppearanceResponseDTO>> getSimpleList() {
        List<AppearanceResponseDTO> list = repository.findAll().stream()
            .map(AppearanceMapper::toSimpleDTO)
            .toList();
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia listada correctamente");
        return new ResponseDto<>(list,metadata);
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> save(AppearanceRequestDTO dto) {
        AppearanceEntity entity = new AppearanceEntity();
        entity.setLoginBackground(dto.getLogin_background());
        entity.setTypography(dto.getTypography());
        entity.setFixedHeader(dto.getFixed_header());
        entity.setMenuPosition(dto.getMenu_position());
        entity.setCollapsedMenu(dto.getCollapsed_menu());
        entity.setBackgroundColor(dto.getBackground_color());
        entity.setBoxBorder(dto.getBox_border());
        entity.setBoxBackground(dto.getBox_background());
        entity.setActive(true);
        entity.setCreationDate(LocalDateTime.now());
        AppearanceEntity saved = repository.save(entity);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia creada correctamente");
        return new ResponseDto<>(AppearanceMapper.toDetailDTO(saved), metadata);
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> update(AppearanceRequestDTO request, Integer id) {
        AppearanceEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Apariencia no encontrada"));
        
        entity.setLoginBackground(request.getLogin_background());
        entity.setTypography(request.getTypography());
        entity.setFixedHeader(request.getFixed_header());
        entity.setMenuPosition(request.getMenu_position());
        entity.setCollapsedMenu(request.getCollapsed_menu());
        entity.setBackgroundColor(request.getBackground_color());
        entity.setBoxBorder(request.getBox_border());
        entity.setBoxBackground(request.getBox_background());
        entity.setModificationDate(LocalDateTime.now());
        AppearanceEntity updated = repository.save(entity);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia actualizada");
        return new ResponseDto<>(AppearanceMapper.toDetailDTO(updated),metadata);
    }

    @Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected = repository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia desactivada");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

    @Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected = repository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia activada");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
}
