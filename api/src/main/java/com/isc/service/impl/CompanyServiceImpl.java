package com.isc.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.CompanyRequestDTO;
import com.isc.dto.response.CompanyDetailResponseDTO;
import com.isc.dto.response.CompanyResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.CompanyEntity;
import com.isc.mapper.CompanyMapper;
import com.isc.repository.CompanyRepository;
import com.isc.service.CompanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
	private final CompanyRepository companyRepository;

	@Override
    public ResponseDto<List<CompanyDetailResponseDTO>> getAllDetails() {
        List<CompanyDetailResponseDTO> response = companyRepository.findAllByStatusTrue().stream()
                .map(CompanyMapper::toDetailDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empresas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<CompanyResponseDTO>> getSimpleList() {
        List<CompanyResponseDTO> response = companyRepository.findAllByStatusTrue().stream()
                .map(CompanyMapper::toSimpleDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empresas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<CompanyDetailResponseDTO> save(CompanyRequestDTO request) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCreationUser("system"); // Reemplazar con usuario real
        entity.setCreationIp("127.0.0.1"); // Reemplazar con IP real
        
        entity = companyRepository.save(entity);
        CompanyDetailResponseDTO response = CompanyMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Empresa creada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<CompanyDetailResponseDTO> update(CompanyRequestDTO request, Integer id) {
        CompanyEntity entity = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
        
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setModificationDate(LocalDateTime.now());
        entity.setModificationUser("system"); // Reemplazar con usuario real
        entity.setModificationIp("127.0.0.1"); // Reemplazar con IP real
        
        entity = companyRepository.save(entity);
        CompanyDetailResponseDTO response = CompanyMapper.toDetailDto(entity);
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empresa actualizada correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = companyRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo desactivar la empresa con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empresa desactivada correctamente");
        MessageResponseDTO message = new MessageResponseDTO("Empresa desactivada correctamente");
        return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = companyRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la empresa con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Empresa activada correctamente");
        MessageResponseDTO message = new MessageResponseDTO("Empresa activada correctamente");
        return new ResponseDto<>(message, metadata);
    }
}
