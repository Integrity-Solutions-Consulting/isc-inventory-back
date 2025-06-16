package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.InvoiceDetailRequestDTO;
import com.isc.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentCategoryEntity;
import com.isc.entitys.InvoiceDetailEntity;
import com.isc.mapper.InvoiceDetailMapper;
import com.isc.repository.EquipmentCategoryRepository;
import com.isc.repository.InvoiceDetailRepository;
import com.isc.service.InvoiceDetailService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl implements InvoiceDetailService{
	private final InvoiceDetailRepository invoiceDetailRepository;
	private final EquipmentCategoryRepository equipmentCategoryRepository;

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
	public ResponseDto<InvoiceDetailEntityDetailResponseDTO> save(InvoiceDetailRequestDTO request) {
		EquipmentCategoryEntity equipCate = equipmentCategoryRepository.findById(request.getCategory())
				.orElseThrow(() -> new RuntimeException("Categoria de equipo no encontrado"));
		InvoiceDetailEntity entity = new InvoiceDetailEntity();
		entity.setCategory(equipCate);
		entity.setDescription(request.getDescription());;
		entity.setUnitPrice(request.getUnitPrice());
		entity.setQuantity(request.getQuantity());
		entity.setSubtotal(request.getSubtotal());
		entity.setTax(request.getTax());
		entity.setDiscount(request.getDiscount());
		entity.setTotal(request.getTotal());
		entity = invoiceDetailRepository.save(entity);
		InvoiceDetailEntityDetailResponseDTO response = InvoiceDetailMapper.toInvoiceDetailEntityDetailResponseDTO(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Detalles de factura cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<InvoiceDetailEntityDetailResponseDTO> update(InvoiceDetailRequestDTO request, Integer id) {
		InvoiceDetailEntity entity = invoiceDetailRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Detalles de factura no encontrada"));
		if(entity.getCategory().getId()!=request.getCategory()) {
			EquipmentCategoryEntity equipCate = equipmentCategoryRepository.findById(request.getCategory())
		            .orElseThrow(() -> new RuntimeException("Categoria de equipo no encontrada"));
		    entity.setCategory(equipCate);;
		}
		entity.setDescription(request.getDescription());;
		entity.setUnitPrice(request.getUnitPrice());
		entity.setQuantity(request.getQuantity());
		entity.setSubtotal(request.getSubtotal());
		entity.setTax(request.getTax());
		entity.setDiscount(request.getDiscount());
		entity.setTotal(request.getTotal());
		entity = invoiceDetailRepository.save(entity);
		InvoiceDetailEntityDetailResponseDTO response = InvoiceDetailMapper.toInvoiceDetailEntityDetailResponseDTO(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
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
