package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.dto.request.CustomerRequestDTO;
import com.isc.api.dto.response.CustomerDetailResponseDTO;
import com.isc.api.dto.response.CustomerResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.CustomerEntity;
import com.isc.api.mapper.CustomerMapper;
import com.isc.api.repository.CustomerRepository;
import com.isc.api.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;    
    
//buscar por RUC
    @Override
    public ResponseDto<CustomerDetailResponseDTO> findByRuc(String ruc) {
        CustomerEntity entity = customerRepository.findByRuc(ruc)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con RUC: " + ruc));
        
        return new ResponseDto<>(CustomerMapper.toDetailDTO(entity), 
                new MetadataResponseDto(HttpStatus.OK, "Cliente encontrado"));
    }
    
    
    @Override
    public ResponseDto<List<CustomerDetailResponseDTO>> getAllDetails() {
        List<CustomerDetailResponseDTO> list = customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDetailDTO)
                .collect(Collectors.toList());

        return new ResponseDto<>(list, new MetadataResponseDto(HttpStatus.OK, "Lista completa de clientes"));
    }

    @Override
    public ResponseDto<List<CustomerResponseDTO>> getSimpleList() {
        List<CustomerResponseDTO> list = customerRepository.findAllByStatusTrue()
                .stream()
                .map(CustomerMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new ResponseDto<>(list, new MetadataResponseDto(HttpStatus.OK, "Lista simple de clientes activos"));
    }

    @Override
    @Transactional
    public ResponseDto<CustomerDetailResponseDTO> save(CustomerRequestDTO request) {
        CustomerEntity entity = CustomerMapper.toEntity(request);
        entity.setCreationDate(LocalDateTime.now());
        entity.setStatus(true);

        CustomerEntity saved = customerRepository.save(entity);
        return new ResponseDto<>(CustomerMapper.toDetailDTO(saved), new MetadataResponseDto(HttpStatus.CREATED, "Cliente creado"));
    }

    @Override
    @Transactional
    public ResponseDto<CustomerDetailResponseDTO> update(CustomerRequestDTO request, String ruc) {
        CustomerEntity entity = customerRepository.findByRuc(ruc)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con RUC: " + ruc));

        CustomerMapper.updateEntityFromDTO(entity, request);
        entity.setModificationDate(LocalDateTime.now());

        CustomerEntity updated = customerRepository.save(entity);
        return new ResponseDto<>(CustomerMapper.toDetailDTO(updated), new MetadataResponseDto(HttpStatus.OK, "Cliente actualizado"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> inactive(String ruc) {
        int updated = customerRepository.inactive(ruc);
        if (updated == 0) {
            throw new RuntimeException("No se pudo desactivar el cliente con RUC: " + ruc);
        }

        return new ResponseDto<>(new MessageResponseDTO("Cliente desactivado"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> active(String ruc) {
        int updated = customerRepository.active(ruc);
        if (updated == 0) {
            throw new RuntimeException("No se pudo activar el cliente con ID: " + ruc);
        }

        return new ResponseDto<>(new MessageResponseDTO("Cliente activado"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }
}
