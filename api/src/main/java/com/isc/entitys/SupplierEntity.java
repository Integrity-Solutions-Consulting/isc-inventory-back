package com.isc.entitys;

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
@Table(name = "equipment_category", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "business_name", length = 150)
	private String businessName;
	
	@Column(length = 200)
	private String address;
	
	@Column(length = 15)
	private String phone;
	
	@Column(length = 100)
	private String email;
	
	@Column(name = "tax_id", length = 50)
    private String taxId;
	
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