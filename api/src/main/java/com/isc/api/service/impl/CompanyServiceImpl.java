package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.isc.api.dto.request.CompanyRequestDTO;
import com.isc.api.dto.response.CompanyDetailResponseDTO;
import com.isc.api.dto.response.CompanyResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.CompanyEntity;
import com.isc.api.repository.CompanyRepository;
import com.isc.api.service.CompanyService;
import com.isc.api.mapper.CompanyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseDto<List<CompanyDetailResponseDTO>> getAllDetails() {
        List<CompanyDetailResponseDTO> list = repository.findAll().stream()
            .map(companyMapper::toDetailDTO)
            .toList();
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañías listadas con detalles correctamente");
        return new ResponseDto<>(list, metadata);
    }

    @Override
    public ResponseDto<List<CompanyResponseDTO>> getSimpleList() {
        List<CompanyResponseDTO> list = repository.findAll().stream()
            .map(companyMapper::toResponseDTO)
            .toList();
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañías listadas correctamente");
        return new ResponseDto<>(list,metadata);
    }

    @Override
    public ResponseDto<CompanyDetailResponseDTO> save(CompanyRequestDTO request) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setRuc(request.getRuc());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setCreationDate(LocalDateTime.now());
        CompanyEntity saved = repository.save(entity);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañia creada correctamente");
        return new ResponseDto<>(companyMapper.toDetailDTO(saved), metadata);
    }

    @Override
    public ResponseDto<CompanyDetailResponseDTO> update(CompanyRequestDTO request, Integer id) {
        CompanyEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compañía no encontrada"));
        entity.setName(request.getName());
        entity.setRuc(request.getRuc());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setModificationDate(LocalDateTime.now());
        CompanyEntity updated = repository.save(entity);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañia actualizada");
        return new ResponseDto<>(companyMapper.toDetailDTO(updated),metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) 
    {
    	int rowsAffected = repository.inactive(id);
		if(rowsAffected == 0) 
		{
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañia desactivada");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
    	int rowsAffected = repository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Compañia activada");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
    }
}
