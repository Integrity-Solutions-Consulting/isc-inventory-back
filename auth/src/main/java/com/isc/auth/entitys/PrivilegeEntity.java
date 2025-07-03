package com.isc.auth.entitys;

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
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "privileges",
    schema = "authentication",
    uniqueConstraints = @UniqueConstraint(columnNames = {"key", "application_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privileges_id_seq")
    @SequenceGenerator(name = "privileges_id_seq", sequenceName = "authentication.privileges_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
	private String description;

    @Column(nullable = false, length = 25)
    private String key;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "last_modification_date")
    private LocalDateTime lastModificationDate;

    @Column(name = "application_id", nullable = false)
    private Integer applicationId;
}
