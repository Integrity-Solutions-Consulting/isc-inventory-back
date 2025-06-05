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
@Table(name = "warranty_type", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_equipment", nullable = true)
    private EquipmentEntity equipment;
	
	@Column(length = 255)
	private String conditions;
		
	@Column(name = "warranty_start_date")
	private LocalDateTime warrantyStartDate;
	
	@Column(name = "warranty_end_date")
	private LocalDateTime warrantyEndDate;
	
	@Column(name = "support_contact", length = 100)
    private String SupportContact;
	
	@Column(name = "warranty_status")
    private Short warrantyStatus;

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
warranty_type
id serial
id_equipment integer
conditions character varying(255)
warranty_start_date date
warranty_end_date date
support_contact character varying(100)
warranty status smallint
status boolean
creation_user character varying(50)
modification_user character varying(50)
creation_date timestamp without time zone
modification_date timestamp without time zone
creation_ip character varying(45)
modification_ip character varying(45)
 
*/