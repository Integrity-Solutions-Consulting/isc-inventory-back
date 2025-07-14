package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.api.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.api.dto.request.EquipmentRequest;
import com.isc.api.dto.request.InvoiceRequestDTO;
import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.*;
import com.isc.api.mapper.EquipmentCategoryMapper;
import com.isc.api.mapper.EquipmentMapper;
import com.isc.api.repository.*;
import com.isc.api.service.EquipmentCharacteristicService;
import com.isc.api.service.EquipmentService;
import com.isc.api.service.InvoiceService;
import com.isc.api.service.WarrantTypeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

	private final EquipmentRepository equipmentRepository;
	private final EquipmentStatusRepository statusRepository;
	private final EquipmentConditionRepository conditionRepository;
	private final EquipmentCategoryRepository categoryRepository;
	private final CompanyRepository companyRepository;
	private final EquipmentCategoryStockRepository categoryStockRepository;
	
	private final EquipmentCharacteristicService characteristService;
	private final InvoiceService invoiceService;
	private final WarrantTypeService warrantyService;
	
	private final Integer outOfService= 7;
	private final Integer available= 1;
	private final Integer irreparable= 7;

	@Override
	public ResponseDto<List<EquipmentDetailResponseDTO>> getAllDetails() {
		List<EquipmentDetailResponseDTO> response = equipmentRepository.findAll().stream()
				.map(EquipmentMapper::toDetailDto).collect(Collectors.toList());

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<EquipmentResponseDTO>> getSimpleList() {
		List<EquipmentResponseDTO> response = equipmentRepository.findAllByStatusTrue().stream()
				.map(EquipmentMapper::toSimpleDto).collect(Collectors.toList());

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos activos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	@Transactional
	public ResponseDto<EquipmentDetailResponseDTO> save(EquipmentRequest request) {
		EquipmentEntity equipment = new EquipmentEntity();
		if (equipmentRepository.existsBySerialNumber(equipment.getSerialNumber())) {
		    throw new IllegalArgumentException("Serial number already exists");
		}

		if (equipmentRepository.existsByItemCode(equipment.getItemCode())) {
		    throw new IllegalArgumentException("Item code already exists");
		}
		EquipmentConditionEntity condition = conditionRepository.findById(request.getCondition())
				.orElseThrow(() -> new RuntimeException("Estado del equipo no encontrado"));
		CompanyEntity company = companyRepository.findById(request.getCompany())
				.orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
		equipment.setCondition(condition);
		equipment.setCompany(company);
		
		if(request.getCategoryId()!=0) {
			EquipmentCategoryEntity category = categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
			this.upStock(category.getStock());
			equipment.setCategory(category);
			
		}else {
			EquipmentCategoryEntity category = new EquipmentCategoryEntity();
			category.setName(request.getCategoryName());
			category = categoryRepository.save(category);
			equipment.setCategory(category);
			EquipmentCategoryStockEntity stock = new EquipmentCategoryStockEntity();
			stock.setCategory(category);
			stock.setStock(1);
			categoryStockRepository.save(stock);
		}
		
		List<EquipmentCharacteristicEntity> characteristics = new ArrayList<>();
		
		for(EquipmentCharacteristicRequestDTO characteristRequest: request.getEquipmentCharacteristics()) {
			characteristics.add(characteristService.saveForEquipment(characteristRequest, equipment));
		}
		
        EquipmentStatusEntity statusAsignado = new EquipmentStatusEntity();
        statusAsignado.setId(this.available);
		
		equipment.setCharacteristic(characteristics);
		equipment.setEquipStatus(statusAsignado);
		
		equipment.setBrand(request.getBrand());
		equipment.setModel(request.getModel());
		equipment.setSerialNumber(request.getSerialNumber());
		equipment.setItemCode(request.getItemCode());

		// Guardamos el equipo
		equipment = equipmentRepository.save(equipment);
		

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED, "Equipo creado correctamente");
		return new ResponseDto<>(EquipmentMapper.toDetailDto(equipment), metadata);
	}

	@Override
	@Transactional 
	public ResponseDto<EquipmentDetailResponseDTO> update(EquipmentRequest request, Integer id) {
		EquipmentEntity equipment = equipmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		if(equipment.getCondition().getId()==this.irreparable) {
			throw new RuntimeException("Este equipo ya no se puede modificar");
		}
		
		if(!request.getCondition().equals(equipment.getCondition().getId())) {
			EquipmentConditionEntity condition = conditionRepository.findById(request.getCondition())
					.orElseThrow(() -> new RuntimeException("Estado del equipo no encontrado"));
			if(condition.getId()==this.irreparable) {
				this.downStock(equipment.getCategory().getStock());
				EquipmentStatusEntity status = new EquipmentStatusEntity();
				status.setId(this.outOfService);
				equipment.setEquipStatus(status);
				equipment.setStatus(false);
			}
			equipment.setCondition(condition);

		}
		if(!request.getCompany().equals(equipment.getCompany().getId())) {
			CompanyEntity company = companyRepository.findById(request.getCompany())
					.orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
			equipment.setCompany(company);
		}
		
		List<EquipmentCharacteristicEntity> characteristics = new ArrayList<>();
		for(EquipmentCharacteristicRequestDTO characteristRequest: request.getEquipmentCharacteristics()) {
			if( characteristRequest.getId()!=null  && characteristRequest.getId()!=0 ) {
				characteristics.add(characteristService.updateForEntity(characteristRequest));
			}else {
				characteristics.add(characteristService.saveForEquipment(characteristRequest, equipment));
			}
			
		}
		equipment.getCharacteristic().clear();
		equipment.getCharacteristic().addAll(characteristics);
		
		equipment.setBrand(request.getBrand());
		equipment.setModel(request.getModel());
		equipment.setSerialNumber(request.getSerialNumber());
		equipment.setItemCode(request.getItemCode());
		equipment.setModificationDate(LocalDateTime.now());

		equipment = equipmentRepository.save(equipment);

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo actualizado correctamente");
		return new ResponseDto<>(EquipmentMapper.toDetailDto(equipment), metadata);
	}

	@Override
	@Transactional
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		EquipmentEntity equipment = equipmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		if(equipment.getEquipStatus().getId().equals(this.available)) {
			this.downStock(equipment.getCategory().getStock());
		}
		int rowsAffected = equipmentRepository.inactive(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo inactivar el equipo con ID: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo inactivado correctamente");
		return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
	}

	@Override
	@Transactional
	public ResponseDto<MessageResponseDTO> active(Integer id) {
		EquipmentEntity equipment = equipmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		
		if(equipment.getEquipStatus().getId().equals(this.available)) {
			this.upStock(equipment.getCategory().getStock());
		}
		int rowsAffected = equipmentRepository.active(id);
		if (rowsAffected == 0) {
			throw new RuntimeException("No se pudo activar el equipo con ID: " + id);
		}
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo activado correctamente");
		return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
	}

	// Método para cambiar el estado del equipo
	@Override
	@Transactional
	public ResponseDto<MessageResponseDTO> changeStatus(Integer idEquipo, Integer newStatus) {
		EquipmentEntity equipo = equipmentRepository.findById(idEquipo)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

		EquipmentStatusEntity status = statusRepository.findById(newStatus)
				.orElseThrow(() -> new RuntimeException("Estado no encontrado: " + newStatus));
		if(status.getId()==1) {
			this.upStock(equipo.getCategory().getStock());
		}

		equipo.setEquipStatus(status);
		equipmentRepository.save(equipo);

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK,
				"Estado del equipo actualizado correctamente");
		return new ResponseDto<>(new MessageResponseDTO("Estado cambiado a: " + status), metadata);
	}
	
	@Override
	public ResponseDto<EquipmentDetailResponseDTO> setInvoice(Integer idEquipo,InvoiceRequestDTO request){
		EquipmentEntity equipment = equipmentRepository.findById(idEquipo)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		if(request.getId()!=null && request.getId()!=0 ) {
			InvoiceEntity invoice = invoiceService.update(request,request.getId());
			equipment.setInvoice(invoice);
		}else {
			InvoiceEntity invoice = invoiceService.save(request);
			equipment.setInvoice(invoice);
		}
		
		equipment = equipmentRepository.save(equipment);

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo actualizado correctamente");
		return new ResponseDto<>(EquipmentMapper.toDetailDto(equipment), metadata);
	}
	
	@Override
	public ResponseDto<EquipmentDetailResponseDTO> setWarranty(Integer idEquip, WarrantTypeRequestDTO request){
		EquipmentEntity equipment = equipmentRepository.findById(idEquip)
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
		if(request.getId()!=0 || request.getId()!=null) {
			WarrantTypeEntity warranty = warrantyService.update(request,request.getId());
			equipment.setWarranty(warranty);
		}else {
			WarrantTypeEntity warranty = warrantyService.save(request);
			equipment.setWarranty(warranty);
		}
		
		equipment = equipmentRepository.save(equipment);

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipo actualizado correctamente");
		return new ResponseDto<>(EquipmentMapper.toDetailDto(equipment), metadata);
	}
	
	
	private void upStock(EquipmentCategoryStockEntity stock) {
		stock.setStock(stock.getStock()+1);
        categoryStockRepository.save(stock);
	}
	
	private void downStock(EquipmentCategoryStockEntity stock) {
		stock.setStock(stock.getStock()-1);
        categoryStockRepository.save(stock);
	}
	
}