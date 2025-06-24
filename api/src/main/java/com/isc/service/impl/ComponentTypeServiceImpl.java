package com.isc.service.impl;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.ComponentTypeEntity;
import com.isc.mapper.ComponentTypeServiceMapper;
import com.isc.repository.ComponentTypeRepository;
import com.isc.service.ComponentTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentTypeServiceImpl implements ComponentTypeService {

    private ComponentTypeRepository componentTypeRepository;

    @Override
    public ResponseDto<ComponentTypeDetailResponseDTO> save(ComponentTypeRequestDTO request) {
        ComponentTypeEntity entity = ComponentTypeServiceMapper.toEntity(request, "system", "127.0.0.1");
        ComponentTypeEntity saved = componentTypeRepository.save(entity);

        ComponentTypeDetailResponseDTO dto = ComponentTypeServiceMapper.toDetailResponseDTO(saved);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Tipo de componente creado exitosamente");

        return new ResponseDto<>(dto, metadata);
    }

    @Override
    public ResponseDto<ComponentTypeDetailResponseDTO> update(ComponentTypeRequestDTO request, Integer id) {
        ComponentTypeEntity entity = componentTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de componente no encontrado con ID: " + id));

        ComponentTypeServiceMapper.updateEntity(entity, request, "system", "127.0.0.1");
        ComponentTypeEntity updated = componentTypeRepository.save(entity);

        ComponentTypeDetailResponseDTO dto = ComponentTypeServiceMapper.toDetailResponseDTO(updated);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Tipo de componente actualizado correctamente");

        return new ResponseDto<>(dto, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        ComponentTypeEntity entity = componentTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de componente no encontrado con ID: " + id));

        entity.setStatus(false);
        entity.setModificationDate(LocalDateTime.now());
        entity.setModificationUser("system");
        entity.setModificationIp("127.0.0.1");

        componentTypeRepository.save(entity);

        MessageResponseDTO message = new MessageResponseDTO("Tipo de componente inactivado correctamente");
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operación exitosa");

        return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        ComponentTypeEntity entity = componentTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de componente no encontrado con ID: " + id));

        entity.setStatus(true);
        entity.setModificationDate(LocalDateTime.now());
        entity.setModificationUser("system");
        entity.setModificationIp("127.0.0.1");

        componentTypeRepository.save(entity);

        MessageResponseDTO message = new MessageResponseDTO("Tipo de componente activado correctamente");
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operación exitosa");

        return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<List<ComponentTypeDetailResponseDTO>> getAllDetails() {
        List<ComponentTypeEntity> entities = componentTypeRepository.findAll();

        List<ComponentTypeDetailResponseDTO> dtoList = entities.stream()
            .map(ComponentTypeServiceMapper::toDetailResponseDTO)
            .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Lista detallada obtenida correctamente");
        return new ResponseDto<>(dtoList, metadata);
    }

    @Override
    public ResponseDto<List<ComponentTypeResponseDTO>> getSimpleList() {
        List<ComponentTypeEntity> entities = componentTypeRepository.findAll();

        List<ComponentTypeResponseDTO> dtoList = entities.stream()
            .map(ComponentTypeServiceMapper::toSimpleResponseDTO)
            .collect(Collectors.toList());

        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Lista simple obtenida correctamente");
        return new ResponseDto<>(dtoList, metadata);
    }
}
