package com.isc.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customer", schema = "administration", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "email")},
		indexes = {
				@Index(name = "indx_customer_name", columnList = "name"),
		        @Index(name = "indx_customer_phone", columnList = "phone")}
		)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", length = 100, nullable = false, unique = true)
	private String name;

	@Column(name = "address", length = 255)
    private String address;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;	

	@Column(nullable = false)
	private Boolean status = true;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "modification_date")
	private LocalDateTime modificationDate;

	@Column(name = "creation_user", length = 50)
	private String creationUser;

	@Column(name = "modification_user", length = 50)
	private String modificationUser;

	@Column(name = "creation_ip", length = 45)
	private String creationIp;

	@Column(name = "modification_ip", length = 45)
	private String modificationIp;

}