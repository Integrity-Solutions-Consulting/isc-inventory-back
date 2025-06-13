package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.EquipmentCategoryStockRequest;
import com.isc.dto.response.EquipmentCategoryStockResponseDTO;
import com.isc.dto.response.EquipmentCategoryStockDetailResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.EquipmentCategoryStockEntity;
import com.isc.mapper.EquipmentCategoryStockMapper;
import com.isc.repository.EquipmentCategoryStockRepository;
import com.isc.service.EquipmentCategoryStockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryStockServiceImpl implements EquipmentCategoryStockService{
	private final EquipmentCategoryStockRepository equipmentCategoryStockRepository;
	
	@Override
	public ResponseDto<List<EquipmentCategoryStockDetailResponseDTO>> getAllDetails(){
		List<EquipmentCategoryStockDetailResponseDTO> response = equipmentCategoryStockRepository.findAll().stream().map(EquipmentCategoryStockMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Categoria del Stock de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentCategoryStockResponseDTO>> getSimpleList(){
		List<EquipmentCategoryStockResponseDTO> response = equipmentCategoryStockRepository.findAllByStatusTrue().stream()
				.map(EquipmentCategoryStockMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock de las categorias de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> save(EquipmentCategoryStockRequest request){
		EquipmentCategoryStockEntity entity = new EquipmentCategoryStockEntity();
		entity.setCategory(request.getCategory());
		entity.setStock(request.getStock());
		entity = equipmentCategoryStockRepository.save(entity);
		EquipmentCategoryStockDetailResponseDTO response = EquipmentCategoryStockMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock de las categorias de equipo guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> update(EquipmentCategoryStockRequest request, Integer id){
		EquipmentCategoryStockEntity entity = equipmentCategoryStockRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock de las categorias de equipo no encontrado"));
		entity.setCategory(request.getCategory());
		entity.setStock(request.getStock());
		entity.setModificationDate(LocalDateTime.now());
		entity = equipmentCategoryStockRepository.save(entity);
		EquipmentCategoryStockDetailResponseDTO response = EquipmentCategoryStockMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Stock de las categorias de equipo actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentCategoryStockRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentCategoryStockRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
}
