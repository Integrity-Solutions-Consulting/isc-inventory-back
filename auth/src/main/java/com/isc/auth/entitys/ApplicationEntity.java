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
@Table(name = "applications", schema = "authentication", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "name")
	})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applications_seq")
    @SequenceGenerator(name = "applications_seq", sequenceName = "authentication.applications_id_seq", allocationSize = 3)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String image;

    @Column(nullable = false, columnDefinition = "text")
    private String callback;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "creation_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "last_modification_date", columnDefinition = "timestamp")
    private LocalDateTime lastModificationDate;

    @Column(name = "gitlab_repository_id")
    private Integer gitlabRepositoryId;

    @Column(name = "current_version", length = 10)
    private String currentVersion;

    @Column(nullable = false)
    private Boolean visible = true;

    @Column(name = "project_id")
    private Integer projectId;

}
