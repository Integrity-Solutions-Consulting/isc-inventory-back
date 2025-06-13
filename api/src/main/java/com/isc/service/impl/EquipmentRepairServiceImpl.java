package com.isc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.entitys.EquipmentRepairEntity;
import com.isc.entitys.EquipmentEntity;
import com.isc.dto.request.EquipmentRepairRequestDTO;
import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.dto.response.EquipmentRepairResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.mapper.EquipmentRepairMapper;
import com.isc.repository.EquipmentRepairRepository;
import com.isc.repository.EquipmentRepository;
import com.isc.service.EquipmentRepairService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentRepairServiceImpl implements EquipmentRepairService{
	private final EquipmentRepairRepository equipmentRepairRepository;
	private final EquipmentRepository equipmentRepository;
	
	@Override
	public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails(){
		List<EquipmentRepairDetailResponseDTO> response = equipmentRepairRepository.findAll().stream().map(EquipmentRepairMapper::toDetailDto)
				.collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparacion de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList(){
		List<EquipmentRepairResponseDTO> response = equipmentRepairRepository.findAllByStatusTrue().stream()
				.map(EquipmentRepairMapper::toSimpleDto).collect(Collectors.toList());
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparacion de equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request){
		EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
				.orElseThrow(() -> new RuntimeException("Reparacion de equipos no encontrada"));
		
		EquipmentRepairEntity entity = new EquipmentRepairEntity();
		entity.setRepairDate(request.getRepairDate());
		entity.setDescription(request.getDescription());
		entity.setCost(request.getCost());
		entity.setServiceProvider(request.getServiceProvider());
		entity = equipmentRepairRepository.save(entity);
		EquipmentRepairDetailResponseDTO response = EquipmentRepairMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparacion de equipos guardado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id){
		EquipmentRepairEntity entity = equipmentRepairRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reparacion de equipo no encontrada"));
		if (entity.getEquipment().getId()!=request.getEquipment()) {
			EquipmentEntity equipment = equipmentRepository
		            .findById(request.getEquipment())
		            .orElseThrow(() -> new RuntimeException("Equipo no encontrada"));
		    entity.setEquipment(equipment);
		}
		entity.setRepairDate(request.getRepairDate());
		entity.setDescription(request.getDescription());
		entity.setCost(request.getCost());
		entity.setServiceProvider(request.getServiceProvider());
		entity.setModificationDate(LocalDateTime.now());
		entity = equipmentRepairRepository.save(entity);
		EquipmentRepairDetailResponseDTO response = EquipmentRepairMapper.toDetailDto(entity);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparacion de equipos actualizado correctamente");
		return new ResponseDto<>(response, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> inactive(Integer id){
		int rowsAffected =  equipmentRepairRepository.inactive(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
	@Override
	public ResponseDto<MessageResponseDTO> active(Integer id){
		int rowsAffected =  equipmentRepairRepository.active(id);
		if(rowsAffected == 0) {
			 throw new RuntimeException("No se pudo realizar la operacion en el id: " + id);
		}
	    MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Operacion exitosa");
		MessageResponseDTO message = new MessageResponseDTO("Operacion exitosa");
		return new ResponseDto<>(message, metadata);
	}
	
}
