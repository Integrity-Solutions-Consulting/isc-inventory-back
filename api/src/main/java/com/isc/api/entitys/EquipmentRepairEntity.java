package com.isc.api.entitys;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment_repair", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRepairEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_equipment", nullable = false)
    private EquipmentEntity equipment;
	
	@ManyToOne
    @JoinColumn(name = "id_equipment_status", nullable = false)
    private EquipmentStatusEntity repairStatus;
	
	@Column(name = "repair_date")
	private LocalDate repairDate;
	
	@Column(length = 100)
	private String description;
	
	@Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;
	
	@Column(name = "service_provider", length = 150)
    private String serviceProvider;
	
	@Column(nullable = false)
	private Boolean status = true;
	
	@Column(name = "creation_user", length = 50)
	private String creationUser;

	@Column(name = "modification_user", length = 50)
	private String modificationUser;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "modification_date")
	private LocalDateTime modificationDate;
    
	@Column(name = "creation_ip", length = 45)
	private String creationIp;

	@Column(name = "modification_ip", length = 45)
	private String modificationIp;
}