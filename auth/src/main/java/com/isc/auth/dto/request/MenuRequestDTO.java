package com.isc.auth.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDTO {
	@NotBlank(message = "La etiqueta del menú es obligatoria")
	@Size(max = 100, message = "La etiqueta no puede tener más de 100 caracteres")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$", message = "La etiqueta solo puede contener letras y espacios")
	private String label;

	@Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
	private String description;

	@NotBlank(message = "La ruta es obligatoria")
	@Size(max = 255, message = "La ruta no puede tener más de 255 caracteres")
	private String route;

	private Integer parentId; // Puede ser null si es un menú principal

	@Size(max = 100, message = "El nombre del icono no puede tener más de 100 caracteres")
	private String icon;

	@NotNull(message = "El orden es obligatorio")
	@Min(value = 0, message = "El orden debe ser mayor o igual a 0")
	private Integer order;

	@NotNull(message = "Debe indicar si la ruta es interna")
	private Boolean internalRoute;

	@NotNull(message = "El ID de la aplicación es obligatorio")
	private Integer applicationId;
}
