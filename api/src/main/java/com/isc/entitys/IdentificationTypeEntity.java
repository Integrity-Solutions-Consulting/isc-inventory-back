package com.isc.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "identification_type", schema = "administration", uniqueConstraints = {
		@UniqueConstraint(columnNames = "description") })
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class IdentificationTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identification_type_id_seq")
	@SequenceGenerator(name = "identification_type_id_seq", sequenceName = "administration.identification_type_id_seq", allocationSize = 1)
	private Integer id;

	@Column(length = 100, unique = true)
	private String description;

	@Column(nullable = false)
	private Boolean status = true;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "modification_date")
	private LocalDateTime modificationDate;

	@Column(name = "creation_ip", length = 45)
	private String creationIp;

	@Column(name = "modification_ip", length = 45)
	private String modificationIp;
}