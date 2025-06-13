package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.AppearanceRequestDTO;
import com.isc.dto.response.AppearanceDetailResponseDTO;
import com.isc.dto.response.AppearanceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.AppearanceEntity;
import com.isc.mapper.AppearanceMapper;
import com.isc.repository.AppearanceRepository;
import com.isc.service.AppearanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppearanceServiceImpl implements AppearanceService{
	private final AppearanceRepository appearanceRepository;

    @Override
    public ResponseDto<List<AppearanceDetailResponseDTO>> getAllDetails() {
        List<AppearanceDetailResponseDTO> response = appearanceRepository.findAllByActiveTrue().stream().map(AppearanceMapper::toDetailDto).collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencias listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<AppearanceResponseDTO>> getSimpleList() {
        List<AppearanceResponseDTO> response = appearanceRepository.findAllByActiveTrue().stream()
                .map(AppearanceMapper::toSimpleDto).collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencias listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> save(AppearanceRequestDTO request) {
        AppearanceEntity entity = new AppearanceEntity();
        entity = appearanceRepository.save(entity);
        AppearanceDetailResponseDTO response = AppearanceMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia guardada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> update(AppearanceRequestDTO request, Integer id) {
        AppearanceEntity entity = appearanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apariencia no encontrada con ID: " + id));
        entity.setLogin_background(request.getLogin_background());
        entity.setTypography(request.getTypography());
        entity.setFix_header(request.getFix_header());
        entity.setMenu_position(request.getMenu_position());
        entity.setCollapsed_menu(request.getCollapsed_menu());
        entity.setBackground_color(request.getBackground_color());
        entity.setBox_order(request.getBox_order());
        entity.setBox_background(request.getBox_background());
        entity.setModificationDate(LocalDateTime.now());
        entity = appearanceRepository.save(entity);
        AppearanceDetailResponseDTO response = AppearanceMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia actualizada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = appearanceRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo desactivar la apariencia con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia desactivada correctamente");
        MessageResponseDTO message = new MessageResponseDTO("Apariencia desactivada correctamente");
        return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = appearanceRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la apariencia con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Apariencia activada correctamente");
        MessageResponseDTO message = new MessageResponseDTO("Apariencia activada correctamente");
        return new ResponseDto<>(message, metadata);
    }
	
}
