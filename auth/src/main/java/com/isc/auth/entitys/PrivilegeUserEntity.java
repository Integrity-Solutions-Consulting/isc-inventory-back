package com.isc.auth.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "privileges_users",
    schema = "authentication",
    uniqueConstraints = @UniqueConstraint(columnNames = {"privilege_id", "user_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privileges_users_id_seq")
    @SequenceGenerator(name = "privileges_users_id_seq", sequenceName = "authentication.privileges_users_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege_id", nullable = false)
    private PrivilegeEntity privilege;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "last_modification_date")
    private LocalDateTime lastModificationDate;
}