package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.CompanyEntity;
import com.isc.entitys.EquipmentCategoryEntity;
import com.isc.entitys.EquipmentCharacteristicEntity;
import com.isc.entitys.EquipmentEntity;
import com.isc.entitys.EquipmentStatusEntity;
import com.isc.mapper.EquipmentMapper;
import com.isc.repository.CompanyRepository;
import com.isc.repository.EquipmentCategoryRepository;
import com.isc.repository.EquipmentCharacteristicRepository;
import com.isc.repository.EquipmentRepository;
import com.isc.repository.EquipmentStatusRepository;
import com.isc.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService{
	private final EquipmentRepository equipmentRepository;
	private final EquipmentStatusRepository equipmentStatusRepository;
	private final EquipmentCategoryRepository equipmentCategoryRepository;
	private final CompanyRepository companyRepository;
	private final EquipmentCharacteristicRepository equipmentCharacteristicRepository;
	
	@Override
	public ResponseDto<List<EquipmentDetailResponseDTO>> getAllDetails(){
		List<EquipmentDetailResponseDTO> response = equipmentRepository.findAll().stream().map(EquipmentMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Detalles de equipo listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentResponseDTO>> getSimpleList(){
		List<EquipmentResponseDTO> response = equipmentRepository.findAllByStatusTrue().stream()
				.map(EquipmentMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Generos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentDetailResponseDTO> save(EquipmentRequest request){
		EquipmentStatusEntity equipmentStatus = equipmentStatusRepository.findById(request.getEquipStatus())
				.orElseThrow(() -> new RuntimeException("Estado del equipo no encontrado"));;
		EquipmentCategoryEntity equipmentCategory = equipmentCategoryRepository.findById(request.getCategory())
				.orElseThrow(() -> new RuntimeException("Categoria del equipo no encontrada"));;
		CompanyEntity companyCategory = companyRepository.findById(request.getCompany())
				.orElseThrow(() -> new RuntimeException("Compania no encontrada"));;
		EquipmentCharacteristicEntity equipmentCharacteristic = equipmentCharacteristicRepository.findById(request.getCharacteristic())
				.orElseThrow(() -> new RuntimeException("Caracteristicas de equipo no encontrada"));;
		EquipmentEntity entity = new EquipmentEntity();
		entity.setInvoice(request.getInvoice());
		entity.setEquipStatus(equipmentStatus);
		entity.setCategory(equipmentCategory);
		entity.setCompany(companyCategory);
		entity.setCharacteristic(equipmentCharacteristic);
		entity.setBrand(request.getBrand());
		entity.setModel(request.getModel());
		entity.setSerialNumber(request.getSerialNumber());
		entity.setItemCode(request.getItemCode());
		entity = equipmentRepository.save(entity);
		EquipmentDetailResponseDTO response = EquipmentMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Genero guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentDetailResponseDTO> update(EquipmentRequest request, Integer id){
		EquipmentEntity entity = equipmentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		entity.setInvoice(request.getInvoice());
		if(entity.getEquipStatus().getId()!=request.getEquipStatus()) {
			EquipmentStatusEntity equipStatus = equipmentStatusRepository.findById(request.getEquipStatus())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setEquipStatus(equipStatus);
		}
		if(entity.getCategory().getId()!=request.getCategory()) {
			EquipmentCategoryEntity category = equipmentCategoryRepository.findById(request.getCategory())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setCategory(category);
		}
		if(entity.getCompany().getId()!=request.getCompany()) {
			CompanyEntity company = companyRepository.findById(request.getCompany())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setCompany(company);
		}
		if(entity.getCharacteristic().getId()!=request.getCharacteristic()) {
			EquipmentCharacteristicEntity characteristic = equipmentCharacteristicRepository.findById(request.getCharacteristic())
		            .orElseThrow(() -> new RuntimeException("Tipo de identificación no encontrada"));
		    entity.setCharacteristic(characteristic);;
		}
		entity.setBrand(request.getBrand());
		entity.setModel(request.getModel());
		entity.setSerialNumber(request.getSerialNumber());
		entity.setItemCode(request.getItemCode());
		entity = equipmentRepository.save(entity);
		EquipmentDetailResponseDTO response = EquipmentMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Informacion del empleado cargada correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
}
