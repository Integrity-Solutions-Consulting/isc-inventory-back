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
@Table(name = "company", schema = "administration", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
	@SequenceGenerator(name = "company_id_seq", sequenceName = "administration.company_id_seq", allocationSize = 1)
	private Integer id;

	@Column(length = 100, unique = true)
	private String name;

	@Column(length = 255)
	private String description;

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
