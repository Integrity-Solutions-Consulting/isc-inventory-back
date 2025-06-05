package com.isc.entitys;

import java.math.BigDecimal;
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
@Table(name = "invoice_detail", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private EquipmentCategoryEntity category;
	
	@Column(length = 100)
	private String description;
	
	@Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    private Integer quantity;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal tax;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal discount;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    
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
invoice_detail
id serial
id_category integer
description text
unit_price numeric(10,2)
quantity integer
subtotal numeric(10,2)
tax numeric(10,2)
discount numeric(10,2)
total numeric(10,2)
status boolean
creation_user character varying(50)
modification_user character varying(50)
creation_date timestamp without time zone
modification_date timestamp without time zone
creation_ip character varyin9(45)
modification_ip character varying(45)
*/