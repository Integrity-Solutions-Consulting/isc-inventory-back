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
@Table(name = "equipment_category", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentAssignmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeeEntity employee;
	
	@ManyToOne
    @JoinColumn(name = "id_equipment", nullable = false)
    private EquipmentEntity equipment;
	
	@Column(name = "assignment_date")
	private LocalDateTime assigmentDate;
	
	@Column(name = "return_date")
	private LocalDateTime returnDate;
	
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
equipment_assignment
id serial
id_employee integer
id_equipment integer
assignment_date timestamp without time zone
return_date timestamp without time zone
status boolean
creation_user character varying(50)
modification_user character varying(50)
creation_date timestamp without time zone
modification_date timestamp without time zone
creation_ip character varyin9(45)
modification_ip character varying(45)
*/