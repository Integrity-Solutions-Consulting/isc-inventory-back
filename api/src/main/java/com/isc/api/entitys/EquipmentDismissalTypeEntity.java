package com.isc.api.entitys;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment_dismissal_type", schema = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EquipmentDismissalTypeEntity 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

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
    
    @OneToMany(mappedBy = "dismissalType", cascade = CascadeType.ALL)
    private Set<EquipmentDismissalEntity> dismissal;

}
