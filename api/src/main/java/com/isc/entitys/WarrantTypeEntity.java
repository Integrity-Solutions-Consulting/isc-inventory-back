package com.isc.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warranty_type", schema = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_equipment", referencedColumnName = "id", unique=true)
    private EquipmentEntity equipment;

    @Column(length = 255)
    private String conditions;

    @Column(name = "warranty_start_date")
    private LocalDateTime warrantyStartDate;

    @Column(name = "warranty_end_date")
    private LocalDateTime warrantyEndDate;

    @Column(name = "support_contact", length = 100)
    private String supportContact;

    @Column(name = "warranty_status")
    private boolean warrantyStatus =true;

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
