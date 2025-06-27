package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceDetailResponseDTO {
	private Integer id;
	private String login_background;
	private String typography;
	private Boolean fixed_header;
	private String menu_position;
	private Boolean collapsed_menu;
	private String background_color;
	private Integer box_border;
	private String box_background;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	
}