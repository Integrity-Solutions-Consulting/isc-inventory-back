package com.isc.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.EquipmentCategoryStockEntity;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentRepairEntity;
import com.isc.api.entitys.EquipmentStatusEntity;
import com.isc.api.mapper.EquipmentRepairMapper;
import com.isc.api.repository.EquipmentCategoryStockRepository;
import com.isc.api.repository.EquipmentRepairRepository;
import com.isc.api.repository.EquipmentRepository;
import com.isc.api.repository.EquipmentStatusRepository;
import com.isc.api.service.EquipmentRepairService;
import org.springframework.http.HttpStatus;

@Service
public class EquipmentRepairServiceImpl implements EquipmentRepairService {

    @Autowired
    private EquipmentRepairRepository repairRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentStatusRepository statusRepository;
    
    @Autowired  
    private EquipmentCategoryStockRepository categoryStockRepository; 
    
	private final Integer available= 1;
	private final Integer rapairing= 3;
	
	
    @Override
    @Transactional
    public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request) {
        // 1. Buscar el equipo y verifica que no este asginado
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getEquipment()));
        
        if (equipment.getEquipStatus() != null && equipment.getEquipStatus().getId() == 2) 
        {
            MetadataResponseDto repairmessage = new MetadataResponseDto
            		(
                HttpStatus.BAD_REQUEST,
                "No se puede enviar a reparación un equipo asignado. Primero desasigna el equipo."
            );
            return new ResponseDto<>(null, repairmessage);
        }
        
        if (equipment.getEquipStatus() != null && equipment.getEquipStatus().getId() == rapairing)
        {
        	MetadataResponseDto repairmessage = new MetadataResponseDto
            		(
                HttpStatus.BAD_REQUEST,
                "El equipo ya se encuentra en reparacion."
            );
            return new ResponseDto<>(null, repairmessage);
        }
    
        // 2. Buscar o crear el estado "EN_REPARACION"
        
        EquipmentStatusEntity repairStatus = new EquipmentStatusEntity();
        repairStatus.setId(rapairing);
       

        // 3. Asignar el estado al equipo y guardar
        equipment.setEquipStatus(repairStatus);
        equipment.setModificationDate(LocalDateTime.now());
        equipmentRepository.save(equipment);

        // 4. Crear la reparación
        EquipmentRepairEntity repair = EquipmentRepairMapper.toEntity(request, equipment);
        repair.setCreationDate(LocalDateTime.now());
        repair.setStatus(true);
        EquipmentRepairEntity savedRepair = repairRepository.save(repair);

        // 5. Convertir a DTO de respuesta
        EquipmentRepairDetailResponseDTO responseDTO = EquipmentRepairMapper.toDetailDto(savedRepair);

        // 6. Crear metadatos y respuesta
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.CREATED,"Reparación registrada correctamente");
        return new ResponseDto<>(responseDTO, metadata);
    }


    @Override
    public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails()
    {
    	List<EquipmentRepairDetailResponseDTO> response= repairRepository.findAll().stream()
    			.map(EquipmentRepairMapper::toDetailDto).collect(Collectors.toList());
    	
    	MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos listados correctamente");
		return new ResponseDto<>(response, metadata);
    } 
    
    
    @Override
    public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList()
    {
    	List<EquipmentRepairResponseDTO> response = repairRepository.findAllByStatusTrue().stream()
    			.map(EquipmentRepairMapper::toSimpleDto).collect(Collectors.toList());
    	
    	MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Equipos activos listados correctamente");
		return new ResponseDto<>(response, metadata);
    }

    
    @Override
    @Transactional
    public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id) {
      
        if (request.getCost() == null) 
        {
            MetadataResponseDto metadata = new MetadataResponseDto(
                HttpStatus.BAD_REQUEST,
                "El costo es obligatorio para actualizar una reparación"
            );
            return new ResponseDto<>(null, metadata);
        }
        
        // 2. Buscar la reparación
        EquipmentRepairEntity repair = repairRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reparación no encontrada con ID: " + id));
        
        EquipmentEntity equipment = equipmentRepository.findById(request.getEquipment())
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getEquipment()));

        // 3. Validar que el equipo esté EN_REPARACION (3)
        if (equipment.getEquipStatus() == null && !equipment.getEquipStatus().getId().equals(3)) 
        {
            MetadataResponseDto metadata = new MetadataResponseDto(
                HttpStatus.BAD_REQUEST,
                "El equipo no está en estado de reparación"
            );
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
    public ResponseDto<MessageResponseDTO> inactive(Integer id) 
    {
        EquipmentRepairEntity repair = repairRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reparación no encontrada con ID: " + id));
        
        EquipmentEntity equipment = repair.getEquipment();
        
        if (equipment.getEquipStatus().getId().equals(this.available)) 
        {
            this.downStock(equipment.getCategory().getStock());
        }
        repair.setStatus(false);
        repair.setModificationDate(LocalDateTime.now());
        repairRepository.save(repair);
        
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Reparación inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
    
    private void upStock(EquipmentCategoryStockEntity stock) 
    {
		stock.setStock(stock.getStock()+1);
        categoryStockRepository.save(stock);
	}
    
    private void downStock(EquipmentCategoryStockEntity stock) 
    {
		stock.setStock(stock.getStock()-1);
        categoryStockRepository.save(stock);
	}      
}