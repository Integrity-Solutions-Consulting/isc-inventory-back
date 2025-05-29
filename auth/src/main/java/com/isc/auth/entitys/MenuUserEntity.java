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
@Table(name = "menus_users", schema = "authentication", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
		"menu_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menus_users_id_seq")
	@SequenceGenerator(name = "menus_users_id_seq", sequenceName = "authentication.menus_users_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false)
	private MenuEntity menu;

	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(nullable = false)
	private Boolean active = true;

	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "last_modification_date")
	private LocalDateTime lastModificationDate;
}
