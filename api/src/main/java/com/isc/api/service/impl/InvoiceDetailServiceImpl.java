package com.isc.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.InvoiceDetailRequestDTO;
import com.isc.api.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.api.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.InvoiceDetailEntity;
import com.isc.api.mapper.InvoiceDetailMapper;
import com.isc.api.repository.InvoiceDetailRepository;
import com.isc.api.service.InvoiceDetailService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl implements InvoiceDetailService{
	private final InvoiceDetailRepository invoiceDetailRepository;
	@Override
	public ResponseDto<List<InvoiceDetailEntityDetailResponseDTO>> getAllDetails() {
		List<InvoiceDetailEntityDetailResponseDTO> response = invoiceDetailRepository.findAll().stream().map(InvoiceDetailMapper::toInvoiceDetailEntityDetailResponseDTO)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Detalles de factura listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<InvoiceDetailEntityResponseDTO>> getSimpleList() {
		List<InvoiceDetailEntityResponseDTO> response = invoiceDetailRepository.findAllByStatusTrue().stream().map(InvoiceDetailMapper::toInvoiceDetailEntityResponseDTO)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Detalles de factura listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public InvoiceDetailEntity save(InvoiceDetailRequestDTO request) {
		InvoiceDetailEntity entity = new InvoiceDetailEntity();
		entity.setDescription(request.getDescription());;
		entity.setUnitPrice(request.getUnitPrice());
		entity.setQuantity(request.getQuantity());
		entity.setSubtotal(request.getSubtotal());
		entity.setTax(request.getTax());
		entity.setDiscount(request.getDiscount());
		entity.setTotal(request.getTotal());
		entity = invoiceDetailRepository.save(entity);

		return entity;
	}

	@Override
	public InvoiceDetailEntity update(InvoiceDetailRequestDTO request) {
		InvoiceDetailEntity entity = invoiceDetailRepository.findById(request.getId())
	            .orElseThrow(() -> new RuntimeException("Detalles de factura no encontrada"));
		entity.setDescription(request.getDescription());;
		entity.setUnitPrice(request.getUnitPrice());
		entity.setQuantity(request.getQuantity());
		entity.setSubtotal(request.getSubtotal());
		entity.setTax(request.getTax());
		entity.setDiscount(request.getDiscount());
		entity.setTotal(request.getTotal());
		entity = invoiceDetailRepository.save(entity);
		return entity;
	}

	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		int rowsAffected =  invoiceDetailRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		int rowsAffected =  invoiceDetailRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}

	
}