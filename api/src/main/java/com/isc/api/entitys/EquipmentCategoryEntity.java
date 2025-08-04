package com.isc.api.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment_category", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255)
	private String name;
	
	@OneToOne(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EquipmentCategoryStockEntity stock;
	
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