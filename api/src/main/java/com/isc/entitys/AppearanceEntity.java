package com.isc.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appearance", schema = "administration")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppearanceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 25, nullable = false)
	private String login_background;
	
	@Column(length = 25, nullable = false)
	private String typography;
	
	@Column(name = "fix_header", nullable = false)
	private Boolean fix_header = true;

	@Column(length = 25, nullable = false)
    private String menu_position;
	
	@Column(nullable = false)
	private Boolean collapsed_menu = true;

    @Column(length = 25, nullable = false)
    private String background_color;

    @Column(nullable = false)
    private Integer box_order;
    
    @Column(length = 25, nullable = false)
	private String box_background;
    
    @Column(name = "active", nullable = false)
	private Boolean active = true;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "updated_at")
	private LocalDateTime ModificationDate;
}