package com.isc.api.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment_category_stock", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryStockEntity {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	private EquipmentCategoryEntity category;

	@Column(nullable = false)
	private Integer stock;

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