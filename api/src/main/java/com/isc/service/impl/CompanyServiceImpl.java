package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public ResponseDto<List<CompanyDetailResponseDTO>> getAllDetails() {
        List<CompanyDetailResponseDTO> list = companyRepository.findAll()
                .stream()
                .map(CompanyMapper::toDetailDTO)
                .collect(Collectors.toList());

        return new ResponseDto<>(list, new MetadataResponseDto(HttpStatus.OK, "Lista completa de empresas"));
    }

    @Override
    public ResponseDto<List<CompanyResponseDTO>> getSimpleList() {
        List<CompanyResponseDTO> list = companyRepository.findAllByStatusTrue()
                .stream()
                .map(CompanyMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new ResponseDto<>(list, new MetadataResponseDto(HttpStatus.OK, "Lista simple de empresas activas"));
    }

    @Override
    @Transactional
    public ResponseDto<CompanyDetailResponseDTO> save(CompanyRequestDTO request) {
        CompanyEntity entity = CompanyMapper.toEntity(request);
        entity.setCreationDate(LocalDateTime.now());
        entity.setStatus(true);

        CompanyEntity saved = companyRepository.save(entity);
        return new ResponseDto<>(CompanyMapper.toDetailDTO(saved), new MetadataResponseDto(HttpStatus.CREATED, "Empresa creada"));
    }

    @Override
    @Transactional
    public ResponseDto<CompanyDetailResponseDTO> update(CompanyRequestDTO request, Integer id) {
        CompanyEntity entity = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));

        CompanyMapper.updateEntityFromDTO(entity, request);
        entity.setModificationDate(LocalDateTime.now());

        CompanyEntity updated = companyRepository.save(entity);
        return new ResponseDto<>(CompanyMapper.toDetailDTO(updated), new MetadataResponseDto(HttpStatus.OK, "Empresa actualizada"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int updated = companyRepository.inactive(id);
        if (updated == 0) {
            throw new RuntimeException("No se pudo desactivar la empresa con ID: " + id);
        }

        return new ResponseDto<>(new MessageResponseDTO("Empresa desactivada"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int updated = companyRepository.active(id);
        if (updated == 0) {
            throw new RuntimeException("No se pudo activar la empresa con ID: " + id);
        }

        return new ResponseDto<>(new MessageResponseDTO("Empresa activada"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }
}
