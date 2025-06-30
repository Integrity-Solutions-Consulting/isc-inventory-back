package com.isc.entitys;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "equipment", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_invoice", nullable = true)
	private InvoiceEntity invoice;
	
	@ManyToOne
	@JoinColumn(name = "id_condition", nullable = false)
	private EquipmentConditionEntity condition;
	
	@ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private EquipmentStatusEntity equipStatus;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_category", nullable = false)
    private EquipmentCategoryEntity category;

	@OneToOne(mappedBy = "equipment", cascade = CascadeType.ALL)
	private WarrantTypeEntity warranty;

	@ManyToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
	
	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentCharacteristicEntity> characteristic;
	
	@Column(length = 255)
	private String observations;
	
	@Column(length = 100)
	private String brand;
	
	@Column(length = 100)
	private String model;
	
	@Column(name = "serial_number", length = 100)
	private String serialNumber;
	
	@Column(name = "item_code", length = 100)
	private String itemCode;
	
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