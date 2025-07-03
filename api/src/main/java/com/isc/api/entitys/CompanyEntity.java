package com.isc.api.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company", schema = "inventory", uniqueConstraints = 
{
    @UniqueConstraint(columnNames = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity 
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "inventory.company_id_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "tax_id", length = 20)
    private String taxId;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

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
