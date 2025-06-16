package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
public class CustomerServiceImpl implements CustomerService{
	private final CustomerRepository customerRepository;

	@Override
	public ResponseDto<List<CustomerDetailResponseDTO>> getAllDetails() {
		List<CustomerDetailResponseDTO> response = customerRepository.findAll().stream().map(CustomerMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Clientes listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<CustomerResponseDTO>> getSimpleList() {
		List<CustomerResponseDTO> response = customerRepository.findAllByStatusTrue().stream().map(CustomerMapper::toSimpleDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Clientes listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<CustomerDetailResponseDTO> save(CustomerRequestDTO request) {
		CustomerEntity entity = new CustomerEntity();
		entity.setName(request.getName());
		entity.setAddress(request.getAddress());
		entity.setEmail(request.getEmail());
		entity.setPhone(request.getPhone());
		entity = customerRepository.save(entity);
		CustomerDetailResponseDTO response = CustomerMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Cliente guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<CustomerDetailResponseDTO> update(CustomerRequestDTO request, Integer id) {
		CustomerEntity entity = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		entity.setName(request.getName());
		entity.setAddress(request.getAddress());
		entity.setEmail(request.getEmail());
		entity.setPhone(request.getPhone());
		entity.setModificationDate(LocalDateTime.now());
		entity = customerRepository.save(entity);
		CustomerDetailResponseDTO response = CustomerMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Cliente actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  customerRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  customerRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	
}
