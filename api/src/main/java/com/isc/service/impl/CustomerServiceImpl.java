package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.dto.request.CustomerRequestDTO;
import com.isc.dto.response.CustomerDetailResponseDTO;
import com.isc.dto.response.CustomerResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.CustomerEntity;
import com.isc.mapper.CustomerMapper;
import com.isc.repository.CustomerRepository;
import com.isc.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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
    public ResponseDto<CustomerDetailResponseDTO> update(CustomerRequestDTO request, Integer id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        CustomerMapper.updateEntityFromDTO(entity, request);
        entity.setModificationDate(LocalDateTime.now());

        CustomerEntity updated = customerRepository.save(entity);
        return new ResponseDto<>(CustomerMapper.toDetailDTO(updated), new MetadataResponseDto(HttpStatus.OK, "Cliente actualizado"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int updated = customerRepository.inactive(id);
        if (updated == 0) {
            throw new RuntimeException("No se pudo desactivar el cliente con ID: " + id);
        }

        return new ResponseDto<>(new MessageResponseDTO("Cliente desactivado"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }

    @Override
    @Transactional
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int updated = customerRepository.active(id);
        if (updated == 0) {
            throw new RuntimeException("No se pudo activar el cliente con ID: " + id);
        }

        return new ResponseDto<>(new MessageResponseDTO("Cliente activado"), new MetadataResponseDto(HttpStatus.OK, "Operación exitosa"));
    }
}
