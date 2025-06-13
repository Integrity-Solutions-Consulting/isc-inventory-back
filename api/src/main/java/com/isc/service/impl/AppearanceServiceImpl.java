package com.isc.service.impl;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppearanceServiceImpl implements AppearanceService {

    private final AppearanceRepository appearanceRepository;

    @Override
    public ResponseDto<List<AppearanceDetailResponseDTO>> getAllDetails() {
        List<AppearanceEntity> entities = appearanceRepository.findAll();
        List<AppearanceDetailResponseDTO> dtos = entities.stream()
                .map(AppearanceMapper::toDetailDto)
                .collect(Collectors.toList());
        return new ResponseDto<>(dtos, new MetadataResponseDto(HttpStatus.OK, "OK"));
    }

    @Override
    public ResponseDto<List<AppearanceResponseDTO>> getSimpleList() {
        List<AppearanceEntity> entities = appearanceRepository.findAllByActiveTrue();
        List<AppearanceResponseDTO> dtos = entities.stream()
                .map(AppearanceMapper::toSimpleDto)
                .collect(Collectors.toList());
        return new ResponseDto<>(dtos, new MetadataResponseDto(HttpStatus.OK, "OK"));
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> save(AppearanceRequestDTO request) {
        AppearanceEntity entity = AppearanceMapper.toEntity(request);
        entity.setCreationDate(LocalDateTime.now());
        entity.setActive(true);
        AppearanceEntity saved = appearanceRepository.save(entity);
        return new ResponseDto<>(AppearanceMapper.toDetailDto(saved), new MetadataResponseDto(HttpStatus.CREATED, "Created"));
    }

    @Override
    public ResponseDto<AppearanceDetailResponseDTO> update(AppearanceRequestDTO request, Integer id) {
        Optional<AppearanceEntity> optional = appearanceRepository.findById(id);
        if (optional.isEmpty()) {
            return new ResponseDto<>(null, new MetadataResponseDto(HttpStatus.NOT_FOUND, "No Encontrado"));
        }

        AppearanceEntity entity = optional.get();
        AppearanceEntity updatedEntity = AppearanceMapper.toEntity(request);
        updatedEntity.setId(entity.getId());
        updatedEntity.setModificationDate(LocalDateTime.now());
        updatedEntity.setActive(entity.getActive());

        AppearanceEntity saved = appearanceRepository.save(updatedEntity);
        return new ResponseDto<>(AppearanceMapper.toDetailDto(saved), new MetadataResponseDto(HttpStatus.OK, "Updated"));
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int updated = appearanceRepository.inactive(id);
        if (updated == 0) {
            return new ResponseDto<>(
                    new MessageResponseDTO("Appearance not found or already inactive"),
                    new MetadataResponseDto(HttpStatus.NOT_MODIFIED, "Not Modified")
            );
        }
        return new ResponseDto<>(
                new MessageResponseDTO("Appearance inactivated successfully"),
                new MetadataResponseDto(HttpStatus.OK, "OK")
        );
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int updated = appearanceRepository.active(id);
        if (updated == 0) {
            return new ResponseDto<>(
                    new MessageResponseDTO("Appearance not found or already active"),
                    new MetadataResponseDto(HttpStatus.NOT_MODIFIED, "Not Modified")
            );
        }
        return new ResponseDto<>(
                new MessageResponseDTO("Appearance activated successfully"),
                new MetadataResponseDto(HttpStatus.OK, "OK")
        );
    }
}
