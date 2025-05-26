package com.isc.auth.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menus", schema = "authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "label", nullable = false, length = 100)
	private String label;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(name = "route", nullable = false, length = 100)
	private String route;

	@Column(name = "internal_route", nullable = false)
	private Boolean internalRoute = true;

	@Column(name = "application_id")
	private Integer applicationId;

	@Column(name = "parent_id")
	private Integer parentId;

	@Column(name = "active", nullable = false)
	private Boolean active = true;

	@Column(name = "icon", length = 100)
	private String icon;

	@Column(name = "\"order\"", nullable = false)
	private Integer order;

	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "last_modification_date")
	private LocalDateTime lastModificationDate;
}
