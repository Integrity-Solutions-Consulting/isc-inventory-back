package com.isc.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceRequestDTO {
	
	@NotBlank(message = "El fondo del login no puede estar vacío")
	private String login_background;
    
	@NotBlank(message = "La tipografía no puede estar vacía")
	private String typography;
    
    @NotNull(message = "Debe especificar si el encabezado es fijo")
    private Boolean fixed_header;
    
    @NotBlank(message = "Debe indicar la posición del menú")
    @Pattern(regexp = "^(left|top|right|bottom)$", message = "La posición del menú debe ser: left, top, right o bottom")
    private String menu_position;
    
    @NotNull(message = "Debe especificar si el menú está colapsado")
    private Boolean collapsed_menu;
    
    @NotBlank(message = "El color de fondo no puede estar vacío")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "El color de fondo debe estar en formato hexadecimal (por ejemplo: #FFFFFF)")
    private String background_color;
    
    @NotNull(message = "Debe proporcionar el orden de la caja")
    @Min(value = 0, message = "El orden de la caja no puede ser negativo")
    private Integer box_border;
    
    @NotBlank(message = "El fondo de la caja no puede estar vacío")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "El fondo de la caja debe estar en formato hexadecimal")
    private String box_background;
}
