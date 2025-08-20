package com.isc.api.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.request.EquipmentRevokeRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

import com.isc.api.entitys.EquipmentAssignmentEntity;
import com.isc.api.entitys.EquipmentCategoryStockEntity;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentRepairEntity;
import com.isc.api.entitys.EquipmentStatusEntity;
import com.isc.api.mapper.EquipmentRepairMapper;
import com.isc.api.repository.EquipmentAssignmentRepository;
import com.isc.api.repository.EquipmentCategoryStockRepository;
import com.isc.api.repository.EquipmentRepairRepository;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.repository.EquipmentStatusRepository;
import com.isc.api.service.EquipmentAssignmentService;
import com.isc.api.service.EquipmentRepairService;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class EquipmentRepairServiceImpl implements EquipmentRepairService {

	private final EquipmentRepairRepository repairRepository;
	private final EquipmentRepository equipmentRepository;
	private final EquipmentStatusRepository statusRepository;
	private final EquipmentCategoryStockRepository categoryStockRepository;
	private final EquipmentAssignmentRepository assignmentRepository;
	private final EquipmentStatusRepository equipmentStatusRepository;
	private final EquipmentAssignmentService assignmentService;
	private final Integer available = 1;

	private final Integer enRevision = 4;

	@Override
	@Transactional
	public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request) 
	{
		// 1. Buscar el equipo y verifica que no este asginado
		EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getEquipment()));
		System.out.println(equipment.getEquipStatus().getId());
		if (equipment.getEquipStatus() != null && (equipment.getEquipStatus().getId() == 3 || equipment.getEquipStatus().getId() == 4 || equipment.getEquipStatus().getId() == 5 || equipment.getEquipStatus().getId() == 6) ) {
			MetadataResponseDto repairmessage = new MetadataResponseDto(HttpStatus.BAD_REQUEST,
					"El equipo ya se encuentra en reparacion.");
			return new ResponseDto<>(null, repairmessage);
		}

		// 2. Buscar o crear el estado "EN_REVISION"

		EquipmentStatusEntity newStatus = equipmentStatusRepository.findById(enRevision)
	            .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + enRevision)); 
		
		// 4. Crear entidad de reparación
	    EquipmentRepairEntity repairEntity = EquipmentRepairMapper.toEntity(request, equipment);
	    repairEntity.setRepairStatus(newStatus);
	    repairEntity.setStatus(true);

	    // 5. Guardar la reparación
	    EquipmentRepairEntity saved = repairRepository.save(repairEntity);

	    // 6. Actualizar el estado del equipo
	    equipment.setEquipStatus(newStatus);
	    equipmentRepository.save(equipment);
		
	    if (request.isRevoke()) {
	        EquipmentAssignmentEntity assignmentEntity = assignmentRepository
	            .findTopByEquipment_IdOrderByAssignmentDateDesc(request.getEquipment())
	            .orElseThrow(() -> new RuntimeException("No se ha encontrado una asignación de este equipo"));

	       
	        EquipmentRevokeRequestDTO revokeDTO = new EquipmentRevokeRequestDTO();
	        revokeDTO.setRevokeDate(LocalDate.now());
	        revokeDTO.setCondition(request.getCondition());

	        this.assignmentService.revoke(assignmentEntity.getId(), revokeDTO);
	    }



		
		// 6. Crear metadatos y respuesta
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,
				"Reparación registrada correctamente");
		return new ResponseDto<>(EquipmentRepairMapper.toDetailDto(saved), metadata);
	}
	


	@Override
	public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails() {
		List<EquipmentRepairDetailResponseDTO> response = repairRepository.findAll().stream()
				.map(EquipmentRepairMapper::toDetailDto).collect(Collectors.toList());

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList() {
		List<EquipmentRepairResponseDTO> response = repairRepository.findAllByStatusTrue().stream()
				.map(EquipmentRepairMapper::toSimpleDto).collect(Collectors.toList());

		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos activos listados correctamente");
		return new ResponseDto<>(response, metadata);
	}

	@Override
	@Transactional
	public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id) {

		if (request.getCost() == null) {
			MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.BAD_REQUEST,
					"El costo es obligatorio para actualizar una reparación");
			return new ResponseDto<>(null, metadata);
		}

		// 2. Buscar la reparación
		EquipmentRepairEntity repair = repairRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reparación no encontrada con ID: " + id));

		EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
				.orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getEquipment()));

		// 3. Validar que el equipo esté EN_REPARACION (3)
		if (equipment.getEquipStatus() == null && !equipment.getEquipStatus().getId().equals(3)) {
			MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.BAD_REQUEST,
					"El equipo no está en estado de reparación");
			return new ResponseDto<>(null, metadata);
		}

		// 4. Actualizar los datos de la reparación (incluyendo cost)
		repair.setDescription(request.getDescription());
		repair.setServiceProvider(request.getServiceProvider());
		repair.setCost(request.getCost());
		repair.setModificationDate(LocalDateTime.now());

		// 5. Cambiar estado a DISPONIBLE (1)
		EquipmentStatusEntity availableStatus = statusRepository.findById(available)
				.orElseThrow(() -> new RuntimeException("Estado DISPONIBLE no configurado"));

		equipment.setEquipStatus(availableStatus);
		equipment.setModificationDate(LocalDateTime.now());

		// 6. Aumentar stock si aplica
		if (equipment.getStatus() && equipment.getCategory() != null) {
			this.upStock(equipment.getCategory().getStock());
		}

		// 7. Guardar cambios
		equipmentRepository.save(equipment);
		EquipmentRepairEntity savedRepair = repairRepository.save(repair);

		// 8. Convertir a DTO y retornar
		EquipmentRepairDetailResponseDTO responseDTO = EquipmentRepairMapper.toDetailDto(savedRepair);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparación actualizada correctamente");
		return new ResponseDto<>(responseDTO, metadata);
	}

	@Override
	@Transactional
	public ResponseDto<MessageResponseDTO> inactive(Integer id) {
		EquipmentRepairEntity repair = repairRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reparación no encontrada con ID: " + id));

		EquipmentEntity equipment = repair.getEquipment();

		if (equipment.getEquipStatus().getId().equals(this.available)) {
			this.downStock(equipment.getCategory().getStock());
		}
		repair.setStatus(false);
		repair.setModificationDate(LocalDateTime.now());
		repairRepository.save(repair);

				
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparación inactivada correctamente");
		return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
	}

	@Override
	@Transactional
	public EquipmentRepairEntity registerRepairDate(Integer idRepair, EquipmentStatusEntity status) {
		// Buscar la reparación pendiente (activa y sin repairDate)
		EquipmentRepairEntity repair = repairRepository.findById(idRepair)
				.orElseThrow(() -> new RuntimeException(
						"No se encontró reparación con ID: " + idRepair));

		// Asignar la fecha actual
		repair.setRepairStatus(status);;
		repair.setRepairDate(LocalDate.now());
		repair.setModificationDate(LocalDateTime.now());

		// Guardar cambios
		EquipmentRepairEntity updatedRepair = repairRepository.save(repair);
		return updatedRepair;
	}

	private void upStock(EquipmentCategoryStockEntity stock) {
		stock.setStock(stock.getStock() + 1);
		categoryStockRepository.save(stock);
	}

	private void downStock(EquipmentCategoryStockEntity stock) {
		stock.setStock(stock.getStock() - 1);
		categoryStockRepository.save(stock);
	}
}