package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceRequestDTO {
	private Integer login_background;
    private Integer typography;
    private Integer fix_header;
    private Integer menu_position;
    private Integer collapsed_menu;
    private Integer background_color;
    private Integer box_order;
    private Integer box_background;
}
