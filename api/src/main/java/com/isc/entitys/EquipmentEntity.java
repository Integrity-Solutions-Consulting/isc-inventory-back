package com.isc.entitys;

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
@Table(name = "equipment", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_invoice", nullable = false)
    private InvoiceEntity invoice;
	
	@ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private EquipmentStatusEntity EquipStatus;
	
	@ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private EquipmentCategoryEntity category;
	
	@ManyToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
	
	@ManyToOne
    @JoinColumn(name = "id_characteristic")
    private EquipmentCharacteristicEntity characteristic;
	
	@Column(length = 100)
	private String brand;
	
	@Column(length = 100)
	private String model;
	
	@Column(name = "serial_number", length = 100)
	private String SerialNumber;
	
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

/*
inventory
equipment
id serial
id_invoice integer
id_status integer
id_category integer
id_company integer
id_characteristic integer
brand character varying(100)
model character varying(100)
serial number character varying(150)
item_code character varying(50)
status boolean
creation_user character varying(50)
modification_user character varying (50)
creation_date timestamp without time zone
modification_date timestamp without time zone
creation_ip character varying(45)
modification_ip character varying(45)
*/
