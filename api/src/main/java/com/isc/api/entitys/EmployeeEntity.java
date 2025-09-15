package com.isc.api.entitys;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "employee", schema = "administration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
	
	@Id
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_identification_type")
	private IdentificationTypeEntity identificationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gender")
	private GenderEntity gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_position")
	private PositionEntity position;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_work_mode")
	private WorkModeEntity workMode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nationality")
	private NationalityEntity nationality;

    @Column(name = "first_name", length = 80)
    private String firstName;

    @Column(name = "last_name", length = 80)
    private String lastName;

    @Column(name = "identification", length = 13)
    private String identification;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 255)
    private String address;

    @Column(length = 100)
    private String avatar = "http://172.16.14.5/media/profile/default-avatar.png";
    
    @Column(name = "hire_date")
    private LocalDate contractDate;

    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;

    @Column(name = "status")
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
